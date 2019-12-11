## 一、项目简介

### 1、项目名称

SpringBoot构建电商基础秒杀项目

### 2、内容

使用SpringBoot快速搭建前后端分离的电商基础秒杀项目。项目中会通过应用领域驱动型的分层模型设计方式去完成用户otp注册、登陆、查看、商品列表、进入商品详情以及倒计时秒杀开始后下单购买的基本流程

### 3、环境参数

- java环境：JDK 1.8

- 数据库：mysql5.0

- maven：3.6.1

- 开发工具：IntelliJ IDEA 2019.1

- 框架版本：2.2.1.RELEASE

### 4、联系方式

邮箱：zcyf0809@163.com

微信：zcyf-1314

## 二、项目内容

### 1、结构图

![结构图](C:\Users\13503\AppData\Roaming\Typora\typora-user-images\image-20191211104149443.png)



### 2、运行

- 启动项目后，若端口被占用，通过application.yml修改
- 通过resources\static\getotp.html获取验证码，register.html为注册页面，login.html为登录页面，登陆成功后会进入listitem.html页面，即为商品列表页面，点击任意一行可进入对应商品详情页面，即getitem.html，而createitem.html为创建商品页面

### 3、注意事项

1. UI为Metronic框架，是一个基于bootstrap的付费模板
2. 各类各方法均有注解
3. 用resources/mybatis-generator.xml 生成entity和mapper和mapping中的文件，每次改变最后的table标签中tableName和domainObjectName字段
4. 部分mapper和mapping中的类有后添加的方法
5. 使用BeanUtils.copyProperties()方法时，要求两个类其中的变量名称相同且类型相同，否则需要手动操作这些变量
6. MySQL5.6之后，设置datetime类型的默认值不能为 '00-00-00 00:00:00'，需设置为大于1000的年数