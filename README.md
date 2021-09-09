# 基于ThinkPHP的Android移动应用开发

<p align="left" style="">
  <img src="https://img.shields.io/github/last-commit/hongyoudan/MinLifeApp"></img>
	<img src="https://img.shields.io/github/languages/count/hongyoudan/MinLifeApp"></img>
<img src="https://img.shields.io/github/stars/hongyoudan/MinLifeApp?style=social"></img>
<img src="https://img.shields.io/github/watchers/hongyoudan/MinLifeApp?style=social"></img>
</p>

**整理不易，欢迎 `Star` 和 `Fork` ^_^ ，谢谢~~**



## 一、前言

这是《Android应用程序开发》课程设计源码。

实现了界面设计、后台逻辑编码等前后端设计步骤；实现了移动端模拟发起下单支付请求，后台返回相应的商品详情、价格等数据；实现了“社区”模块模拟发送消息的功能。

使用本源码您需要具有如下的知识储备：熟知MVC架构思想、熟知ThinkPHP的配置和使用、熟知Web UI的使用、熟知AJAX的使用、熟知MySQL数据库的增删改查、了解Okhttp的使用、了解Gson的使用等。

## 二、技术栈

ThinkPHP、MySQL、Html、JQuary、JavaScript、AJAX、Okhttp、Gson等。

## 三、快速启动

### 3.1 最小开发环境

JDK>=1.8

MySQL>=5.7.31

Android Studio>=4.0.0

ThinkPHP>=6.0.0

### 3.2 导入数据表

创建数据库：myapp

运行SQL文件：myapp.sql

### 3.3 启动后台管理系统

终端执行：

```java
cd minlife_backend

php think run
```

浏览器输入：localhost:8000

用户名：cpadmin

密码：admin123

### 3.4 运行Android端

打开Android Studio

File -> Open -> MinLifeApp -> Run'app'

## 四、项目截图

| <img src="https://img-blog.csdnimg.cn/668d68ed0d1c4cb5b87768adbdab6c0c.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/95af39004e28406d9ac9ee37a3e1c22b.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/05f706ab6957452b81063404f7326502.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/8bd22f22900047d7922b7cf35002ab82.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| <img src="https://img-blog.csdnimg.cn/667aaf29f66749c98368eb61653635c2.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/fc6a0ed0565445c5af393a466d9989a9.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/4a14cf56c83242bead8f0e1f9275f220.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/cbd872e31ce0452b9e6facaba833a6c1.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> |
| <img src="https://img-blog.csdnimg.cn/c8a47439bd814741bd1a186e79939280.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/c7bfe638df6f41978f30f1951dc608b1.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/f43169f1b8dc473ea106512c7d48d037.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/66376cee7874486cbfde9fc477d504d8.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> |
| <img src="https://img-blog.csdnimg.cn/2a4fd23b497f41258b4d675a6db11297.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> | <img src="https://img-blog.csdnimg.cn/a5d8b40af871483e9e7bd3b71ba5a2bc.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA57u_6Iy25ZOl5ZOl,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center"></img> |                                                              |                                                              |





