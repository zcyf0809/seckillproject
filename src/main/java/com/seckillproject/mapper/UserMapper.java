package com.seckillproject.mapper;

import com.seckillproject.entity.User;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Dec 05 09:56:28 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Dec 05 09:56:28 CST 2019
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Dec 05 09:56:28 CST 2019
     */
    User selectByPrimaryKey(Integer id);

    User selectByTelphone(String telphone);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Dec 05 09:56:28 CST 2019
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Dec 05 09:56:28 CST 2019
     */
    int updateByPrimaryKey(User record);
}