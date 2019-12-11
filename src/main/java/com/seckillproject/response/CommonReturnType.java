package com.seckillproject.response;

/**
 * @author Zhang Yifei
 * @version 1.0
 * @date 2019/12/5 14:19
 * @description 定义一个通用的返回类型，前端可以读取status判断是服务器错误还是其他错误
 */
public class CommonReturnType {
    /**
     * 表明对应请求的返回处理结果：“success”，“fail”
     */
    private String status;
    /**
     * 若status=success，则data内返回前端需要的json数据
     * 若status=fail，则data内使用通用的错误码格式
     */
    private Object data;
    /**
      * @author Zhang Yifei
      * @date 2019/12/9
      * @param result:结果
      * @return CommonReturnType
      * @description 定义一个通用的创建方法
      */
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result,"success");
    }
    /**
      * @author Zhang Yifei
      * @date 2019/12/9
      * @param result:结果
      * @param status：状态码
      * @return CommonReturnType
      * @description
      */
    public static CommonReturnType create(Object result,String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
