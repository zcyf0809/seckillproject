package com.seckillproject.mapper;

import com.seckillproject.entity.Promo;
import com.seckillproject.service.model.PromoModel;

public interface PromoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Sat Dec 07 14:46:54 CST 2019
     */
    int insert(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Sat Dec 07 14:46:54 CST 2019
     */
    int insertSelective(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Sat Dec 07 14:46:54 CST 2019
     */
    Promo selectByPrimaryKey(Integer id);


    Promo selectByItemId(Integer itemId);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Sat Dec 07 14:46:54 CST 2019
     */
    int updateByPrimaryKeySelective(Promo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Sat Dec 07 14:46:54 CST 2019
     */
    int updateByPrimaryKey(Promo record);
}