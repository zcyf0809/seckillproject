package com.seckillproject.error;

/**
 * @author Zhang Yifei
 * @date 2019/12/5 14:28
 * @description 通用错误类型接口
 */
public interface CommonError {

    public int getErrCode();

    public String getErrMsg();

    public CommonError setErMsg(String errMsg);
}
