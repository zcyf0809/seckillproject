package com.seckillproject.service;

import com.seckillproject.service.model.PromoModel;

/**
 * @author Zhang Yifei
 * @date 2019/12/7 14:48
 */
public interface PromoService {
    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param itemId 商品id
     * @return PromoModel 秒杀模型
     * @description 通过商品id查询秒杀信息
     */
    PromoModel getPromoByItemId(Integer itemId);
}
