package com.seckillproject.service;

import com.seckillproject.error.BusinessException;
import com.seckillproject.service.model.OrderModel;

/**
 * @author Zhang Yifei
 * @date 2019/12/7 9:12
 */
public interface OrderService {

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
    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId, Integer amount) throws BusinessException;

}
