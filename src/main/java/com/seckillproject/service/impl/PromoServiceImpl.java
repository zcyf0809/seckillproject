package com.seckillproject.service.impl;

import com.seckillproject.entity.Promo;
import com.seckillproject.mapper.PromoMapper;
import com.seckillproject.service.PromoService;
import com.seckillproject.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Zhang Yifei
 * @date 2019/12/7 14:50
 * @description PromoService的实现类
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoMapper promoMapper;

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param itemId：商品id
     * @return PromoModel：秒杀模型
     * @description 通过商品id查询秒杀信息
     */
    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        Promo promo = promoMapper.selectByItemId(itemId);
        PromoModel promoModel = convertFromEntity(promo);
        if (promoModel == null) {
            return null;
        }
        //判断秒杀活动状态
        if (promoModel.getStartTime().isAfterNow()) {
            promoModel.setStatus(1);
        }else if (promoModel.getEndTime().isBeforeNow()) {
            promoModel.setStatus(3);
        }else {
            promoModel.setStatus(2);
        }
        return promoModel;
    }

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param promo
     * @return PromoModel
     * @description 将promo转换为PromoModel
     */
    private PromoModel convertFromEntity(Promo promo) {
        if (promo == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promo, promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promo.getPromoItemPrice()));
        promoModel.setStartTime(new DateTime(promo.getStartTime()));
        promoModel.setEndTime(new DateTime(promo.getEndTime()));
        return promoModel;
    }
}
