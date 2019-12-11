package com.seckillproject.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Zhang Yifei
 * @date 2019/12/7 14:28
 * @description 秒杀活动的领域模型
 */
public class PromoModel {

    private Integer id;

    /**
     * 名称
     */
    private String promoName;

    /**
     * 活动状态:1表示未开始，2表示进行中，3表示已结束
     */
    private Integer status;

    /**
     * 开始时间
     */
    private DateTime startTime;

    private DateTime endTime;

    /**
     * 适用商品
     */
    private Integer itemId;

    /**
     * 商品价格
     */
    private BigDecimal promoItemPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
