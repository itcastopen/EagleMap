# 扩展新的功能

在EagleMap中支持扩展新的功能，毕竟目前并没有将所有的地图服务都对接进来，所以扩展新的功能可以满足更多的业务系统的需求。

# 项目结构说明

~~~
├─EagleMap
│  │  
│  ├─eagle-map-admin--------------------------可视化管理系统接口服务
│  ├─eagle-map-api----------------------------RESTful接口服务
│  ├─eagle-map-common-------------------------公共组件
│  ├─eagle-map-config-------------------------配置组件，处理配置文件
│  ├─eagle-map-deploy-------------------------发布打包到tar.gz包
│  ├─eagle-map-server-------------------------实现具体的业务逻辑
│  ├─eagle-map-service------------------------定义接口、mapper
│  ├─Dockerfile-------------------------------制作docker镜像文件
│  ├─pom.xml----------------------------------maven依赖配置文件
│  ├─README.md--------------------------------自述文件
~~~

# 具体方法

通过 新增`interface`或在已有`interface`中新增方法进行功能层面的扩展。

> 举例：需要新增一个服务，查询天气。
>
> 百度地图的接口：https://lbsyun.baidu.com/index.php?title=webapi/weather
>
> 高德地图的接口：https://lbs.amap.com/api/webservice/guide/api/weatherinfo
>
> 接入到EagleMap中的大致步骤如下：
>
> 第一步，在`eagle-map-service`工程中的`com.itheima.em.service`包下进行新增`WeatherService`接口。
>
> 第二步，在`eagle-map-server`工程中的`com.itheima.em.server.service.impl`包下分别实现百度和高德的逻辑。这里建议同时实现这两个服务商，以便保证其功能的可用性。
>
> 第三步，在`eagle-map-api`工程中编写相应的`Controller`，对外提供RESTful接口。建议将swagger注解写完整。
>
> 第四步，在`EagleMap-sdk`工程中编写响应的sdk实现，即可在业务系统中使用。

!> 关于具体的实现类，我们建议统一化命名：<br/>高德的实现类：AMapWeatherServiceImpl，注解的名称为：@Service("AMapWeatherService")<br/>百度的实现类：BaiduWeatherServiceImpl，注解的名称为：@Service("BaiduWeatherService")

!> `public int getOrder(){}` 该方法定义在自动选择服务商时的顺序，数字越小优先级越高。

!> `public ProviderType getProvider() {}` 方法定义了该方法实现的服务商是谁，不能为null。

# EagleMapServiceFactory

EagleMap中提供了`EagleMapServiceFactory`工厂类，在该类中提供了`getService()`方法，通过该方法可以获取到响应的实现类。

获取的方式有两种：

第一种，通过`ProviderType`参数和`Class`接口类型获取。

第二种，只通过`Class`接口参数获取。

第一种方式好理解，看代码逻辑即可。在这里重点解释下第二种方式：

> 通过`ProviderThreadLocal`获取到当前线程中的`ProviderType`，什么时候将`ProviderType`放到`ProviderThreadLocal`中的呢？
>
> 是在`com.itheima.em.interceptor.ProviderInterceptor`拦截器中放入的，该拦截器是将用户请求中的`provider`参数或请求头中的`provider`参数获取到，将值放入到`ProviderThreadLocal`中。
>
> 也就是说，在`Controller`中直接通过第二种方式即可获取到相应的实现类。

# 启动运行

整个EagleMap的启动是在`eagle-map-server`中，通过`EagleMapApplication`启动的。本地测试时需要添加 `-Dspring.profiles.active=local` 参数。如下：

<img src="/_media/run.png" />

!> 正式运行时无需此参数，因为所使用的是tar.gz发布包conf目录下application.yml文件。此文件是在这里：（如果修改了application-local.yml要同步的修改此文件）<img src="/_media/deploy-conf.png" />


