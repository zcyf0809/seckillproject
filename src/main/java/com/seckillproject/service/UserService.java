package com.seckillproject.service;

import com.seckillproject.error.BusinessException;
import com.seckillproject.service.model.UserModel;
import org.springframework.dao.DuplicateKeyException;

/**
 * @author Zhang Yifei
 * @date 2019/12/5 10:51
 */
public interface UserService {
    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param id：用户id
     * @return UserModel:用户模型
     * @description 根据用户id查用户模型
     */
    UserModel getUserById(Integer id);

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param userModel:用户模型
     * @return null
     * @description 注册用户
     */
    void register(UserModel userModel) throws BusinessException, DuplicateKeyException;

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param telphone:电话号
     * @param encrptPassword: 加密密码
     * @return UserModel:用户模型
     * @description 用户校验
     */
    UserModel validateLogin(String telphone , String encrptPassword)throws BusinessException;
}
