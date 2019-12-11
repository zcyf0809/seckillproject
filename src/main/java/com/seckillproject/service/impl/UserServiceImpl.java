package com.seckillproject.service.impl;

import com.seckillproject.entity.User;
import com.seckillproject.entity.UserPassword;
import com.seckillproject.error.BusinessException;
import com.seckillproject.error.EmBusinessError;
import com.seckillproject.mapper.UserMapper;
import com.seckillproject.mapper.UserPasswordMapper;
import com.seckillproject.service.UserService;
import com.seckillproject.service.model.UserModel;
import com.seckillproject.validator.ValidationResult;
import com.seckillproject.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zhang Yifei
 * @date 2019/12/5 10:52
 * @description UserService的实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Autowired
    private ValidatorImpl validator;

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param id：用户id
     * @return UserModel:用户模型
     * @description 根据用户id查用户模型
     */
    @Override
    public UserModel getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }
        UserPassword userPassword = userPasswordMapper.selectByUserId(user.getId());
        return convertFromEntity(user, userPassword);
    }

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param userModel:用户模型
     * @return null
     * @description 注册用户
     */
    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException,DuplicateKeyException {
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ValidationResult result = validator.validate(userModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        User user = convertFromUserModel(userModel);
        //使用insertSelective方法时，如果待插入字段值为null，则不插入该字段，使用数据库的默认值
        userMapper.insertSelective(user);
        userModel.setId(user.getId());
        UserPassword userPassword = convertPasswordFromModel(userModel);
        userPasswordMapper.insertSelective(userPassword);
        return;
    }

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param telphone:电话号
     * @param encrptPassword: 加密密码
     * @return UserModel:用户模型
     * @description 用户登录校验
     */
    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException{
        //通过用户手机号获取用户信息
        User user = userMapper.selectByTelphone(telphone);
        if (user == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPassword userPassword = userPasswordMapper.selectByUserId(user.getId());
        UserModel userModel = convertFromEntity(user,userPassword);
        //将数据库中的密码与输入的密码进行比对
        if (!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param userModel:用户模型
     * @return User：用户对象
     * @description 将userModel转换为User
     */
    private User convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        return user;
    }

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param userModel:用户模型
     * @return UserPassword：用户密码对象
     * @description 将userModel转换为UserPassword
     */
    private UserPassword convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncrptPassword(userModel.getEncrptPassword());
        userPassword.setUserId(userModel.getId());
        return userPassword;
    }

    /**
     * @author Zhang Yifei
     * @date 2019/12/10
     * @param user: 用户对象
     * @param userPassword: 用户密码对象
     * @return UserModel: 用户模型
     * @description 将user和userPassword转换为UserModel
     */
    private UserModel convertFromEntity(User user, UserPassword userPassword) {
        if (user == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        if (userPassword != null) {
            userModel.setEncrptPassword(userPassword.getEncrptPassword());
        }
        return userModel;
    }
}
