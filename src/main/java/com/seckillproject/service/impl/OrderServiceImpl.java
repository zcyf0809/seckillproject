package com.seckillproject.service.impl;

import com.seckillproject.entity.Order;
import com.seckillproject.entity.Sequence;
import com.seckillproject.error.BusinessException;
import com.seckillproject.error.EmBusinessError;
import com.seckillproject.mapper.OrderMapper;
import com.seckillproject.mapper.SequenceMapper;
import com.seckillproject.service.ItemService;
import com.seckillproject.service.OrderService;
import com.seckillproject.service.UserService;
import com.seckillproject.service.model.ItemModel;
import com.seckillproject.service.model.OrderModel;
import com.seckillproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Zhang yifei
 * @date 2019/12/7 9:13
 * @description OrderService的实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param userId 用户id
     * @param amount 数量
     * @param itemId 商品id
     * @param promoId 秒杀id
     * @return OrderModel 订单模型
     * @description 1、前端url上传递秒杀活动id，下单接口内校验对应id是否属于对应商品且活动已开始（使用）
     *              2、直接在下单接口内判断对应商品是否存在秒杀活动，若存在则以秒杀价格下单（会检验两次是否是秒杀商品）
     */
    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId,Integer promoId, Integer amount) throws BusinessException {
        //校验下单状态，商品是否存在，用户是否合法，数量是否正确,活动信息
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }
        if (amount <= 0 || amount > 99) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "购买数量不正确");
        }
        if (promoId != null) {
            //校验活动是否存在适用商品
            if (promoId.intValue() != itemModel.getPromoModel().getId()) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动信息不正确");
                //校验秒杀活动是否正在进行
            }else if (itemModel.getPromoModel().getStatus().intValue() != 2) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动还未开始");
            }
        }

        //落单减库存
        boolean result = itemService.decreaseStock(itemId, amount);
        if (!result) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }
        //订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setPromoId(promoId);
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));

        //生成交易流水号（订单号）
        orderModel.setId(this.generateOrderNo());
        Order order = convertFromModel(orderModel);
        orderMapper.insertSelective(order);

        //增加商品销量
        itemService.increaseSales(itemId,amount);

        //返回前端
        return orderModel;
    }

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param orderModel
     * @return Order
     * @description 将orderModel转换为Order
     */
    private Order convertFromModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        Order order = new Order();
        BeanUtils.copyProperties(orderModel, order);
        order.setItemPrice(orderModel.getItemPrice().doubleValue());
        order.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return order;
    }

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @return String：订单号
     * @description 生成订单号(如果事务createorder下单如果回滚，该下单方法中获得流水号id回滚，使等到的id号可能再一次被使用)
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNo() {
        //订单号16位
        //前8位为时间信息
        StringBuilder stringBuilder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);

        //中间6位为自增序列
        int seq = 0;
        Sequence sequence = sequenceMapper.getSequenceByName("order_info");
        seq = sequence.getCurrentValue();
        sequence.setCurrentValue(sequence.getCurrentValue() + sequence.getStep());
        sequenceMapper.updateByPrimaryKeySelective(sequence);
        String seqStr = String.valueOf(seq);
        for(int i = 0; i < 6-seqStr.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(seqStr);

        //最后2位为分库分表位，暂时写死
        stringBuilder.append("00");

        return stringBuilder.toString();
    }
}
