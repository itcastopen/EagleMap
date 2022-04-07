# 安装

这个安装手册是帮忙您快速在您的电脑上，下载、安装并使用 EagleMap。

# 版本选择

当前推荐的稳定版本为1.0。

# 环境准备

EagleMap 依赖 Java 环境来运行。如果您是从代码开始构建并运行EagleMap，还需要为此配置Maven环境，请确保是在以下版本环境中安装使用:

1. 64 bit OS，支持 Linux/Unix/Mac/Windows，推荐选用 Linux/Unix/Mac。
2. 64 bit JDK 1.8+；[下载](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) & [配置](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)。
3. Maven 3.2.x+；[下载](https://maven.apache.org/download.cgi) & [配置](https://maven.apache.org/settings.html)。

# 下载源码或者安装包

你可以通过源码、压缩包以及docker镜像的方式来获取 EagleMap。

## 源码方式

~~~bash
git clone https://gitee.com/itcastopen/EagleMap.git
cd EagleMap/
mvn -Dmaven.test.skip=true clean install -U

#进入eagle-map-deploy子模块中进行打包，最终打成tar.gz包
cd EagleMap/eagle-map-deploy
mvn -DskipTests=true assembly:single

#解压eagle-map-server.tar.gz，进入bin目录启动服务
tar -xvf eagle-map-server.tar.gz
cd eagle-map-server/bin
sh startup.sh
~~~

## 压缩包方式

~~~bash
#解压eagle-map-server.tar.gz，进入bin目录启动服务
tar -xvf eagle-map-server.tar.gz
cd eagle-map-server/bin
sh startup.sh
~~~

## docker方式

~~~bash
#拉取镜像
docker pull registry.cn-hangzhou.aliyuncs.com/itheima/eagle-map-server:latest

docker create --name eagle-map-server \
-p 8484:8484 \
-v /eaglemap/app/application.yml:/app/eagle-map-server/conf/application.yml \
-v /eaglemap/app/logs:/app/eagle-map-server/logs \
registry.cn-hangzhou.aliyuncs.com/itheima/eagle-map-server:latest

#以上命令指定了端口号、配置文件、日志等信息

docker start eagle-map-server

~~~

## application.yml文件

!> **注意1：在application.yml配置文件中，baidu和amap的配置至少配置一项，否则启动失败。**

!> **注意2：数据库的支持目前只支持MySQL，创建脚本在conf目录下的eaglemap-mysql.sql文件。**

~~~yml
server:
  port: 8484 #RESTful服务端口
#spring:
#  datasource: #数据库的配置
#    driver-class-name: com.mysql.jdbc.Driver
#    url: jdbc:mysql://127.0.0.1:3306/eaglemap?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&useSSL=false
#    username: xxxxx
#    password: xxxxx
eagle:
  #服务模式，可选参数：BASE、COMPLETE，默认：BASE
  #BASE模式中无需配置数据库，只保留基础的地图服务
  service-mode: BASE
  #默认策略，可选参数：BAIDU、AMAP、NONE，默认：NONE
  #如果指定了默认策略，在接口中不指定服务商时才用的策略，如果指定NONE，有系统来决定
  default-provider-strategy: NONE
  baidu-web-api: https://api.map.baidu.com  #百度地图web api服务地址
  baidu-ying-yan-api: http://yingyan.baidu.com #百度地图鹰眼服务地址
  amap-web-api: https://restapi.amap.com #高德地图web api服务地址
  amap-ts-api: https://tsapi.amap.com #高德地图猎鹰服务地址
  admin: #可视化管理系统的配置
    user: eagle #用户名
    password: b09315ea09c6d3b5680094257f1f70e4 #密码：eagle，md5加密后
    #RSA的私钥
    private-key: MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKNHw5pK1Jk0EPflxEMFLXa8UnMCTeZoO2NeAtVuu194mxWdDK6W2sdpV1FMD09n8CKg1qV5vOGqj0w2AacSfSxIcOQuloPN2V4r3STdBuIvp35F88WqVqx8pIZ9WvAIRkwcKDkZ87F3X2S+pf7d1crfbyP3PQ7/iTI8dIUtHANdAgMBAAECgYEAhXkTj5weIEKsoEjF39dqq8YUTAmVLoUEDx15iYlkSA3qEIf6JvoQS7Rz+XQIi+u2JEiATnKukXaAf1cikLWPdgA/Su3HqxPjcWuRw/GEBJcwWMaC06wiB1KBUGrqOK5Se193ZSDFU3yWW+jIoYr9IUKFXAbaEco/kgX2lZVzsA0CQQD3dMhQIjBKtbQWPlsIIiNeIuZvfWtjDWokzZLCGbZZgpd+5AABpV550FqqhzCVJdkG7sSRERlCOG9mEjKYRREXAkEAqOr3qp+nX2wP96TIcwPhurcRoPRLbBZHl5iz3E9Akhoi3V8dHapQejUUy9BZswLU853buwdAxADxrcS6nBjPqwJAS1bJ2ePe0hAqKQ+JBZvnKrTzdYQ42qpt45CXGvNbGjtxRlfCTL39Wpb906dfOjmQrtSpEgWjrA6kk+38a3GztwJBAIdYFW+KEtKbREP5rX6yiohPrPlZJKGLTHvEJ8ELY0JtXKghULP3RhpVM7+GxohvaW91eZzm19b0prDD4321R5cCQB8ZNNC7kpgdXvyEOWu7fau9DmTG8e1EWIOEDALaR9w1VpbUIXKprOVURkaMwE00bBE/2fXJBwlteXP2+8L4vMY=
    #RSA的公钥
    public-key: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjR8OaStSZNBD35cRDBS12vFJzAk3maDtjXgLVbrtfeJsVnQyultrHaVdRTA9PZ/AioNalebzhqo9MNgGnEn0sSHDkLpaDzdleK90k3QbiL6d+RfPFqlasfKSGfVrwCEZMHCg5GfOxd19kvqX+3dXK328j9z0O/4kyPHSFLRwDXQIDAQAB
    token-time: 24 #token有效期，单位：小时
  timeout: 10000 #请求超时时间，单位：毫秒
  trace-image: #轨迹缩略图
    scale: 0.9 #缩放比，默认0.9
    background: "#FFFFFF" #背景颜色
    trace-color: "#6699FF" #轨迹颜色
    stroke-width: 3 #画笔宽度
    width: 300 #生成图片的宽度
    height: 300 #生成图片的高度
#  baidu: #百度相关的配置
#    enable: false #是否启用
#    name: 百度地图 #服务商的名称
#    server: #服务端账号的配置
#      id: xxxxx
#      name: xxxxx
#      ak: xxxxx
#      type: xxxxx
#    browser: #浏览器端账号的配置
#      id: xxxxx
#      name: xxxxx
#      ak: xxxxx
#      type: xxxxx
#    trace-servers: #百度地图中的轨迹服务
#      - id: xxxxx
#        name: xxxxx
#        type: xxxxx
#        date: xxxxx
#      - id: xxxxx
#        name: xxxxx
#        type: xxxxx
#        date: xxxxx
#  amap: #高德相关的配置
#    enable: false #是否启用
#    name: xxxxx #服务商的名称
#    server: #服务端账号的配置
#      name: xxxxx
#      ak: xxxxx
#      type: xxxxx
#    browser: #浏览器端账号的配置
#      name: xxxxx
#      ak: xxxxx
#      sk: xxxxx
#      type: xxxxx
~~~

# 地图服务商key

使用地图服务必须向地图服务商申请key。

> **百度地图：**
>
> web API的申请：https://lbsyun.baidu.com/apiconsole/key?application=key#/home
>
> 鹰眼服务的申请：https://lbsyun.baidu.com/index.php?title=yingyan/guide/getkey

高德地图：

> web API的申请：https://lbs.amap.com/dev/key