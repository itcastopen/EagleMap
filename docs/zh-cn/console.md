# 可视化管理系统

EagleMap提供了可视化管理系统，方便对地图相关的数据进行维护，目前提供的功能有：

- 登录
- 系统配置
- 服务管理
- 终端管理
- 轨迹管理
- 电子围栏管理

# 登录

系统访问地址：http://127.0.0.1:8484/login  （默认端口为8484）

默认登录用户名密码均为：eagle，可在`application-local.yml`配置文件中修改，密码为md5加密后的值。

<img src="/_media/login.png"/>


# 系统配置

该功能提供了对于配置文件中地图服务商的相关配置项。仅提供查询功能。

<img src="/_media/system.png"/>

# 服务管理

服务管理是指轨迹服务，在轨迹相关业务中，首先是需要创建轨迹服务，再做后续的操作，也就是说轨迹服务是最大的一个概念。但是要注意，一个服务商key下面可以有多个轨迹服务。

<img src="/_media/TraceServer.png"/>

在服务管理中，可以查看对应服务下的终端：

<img src="/_media/Terminal.png"/>

# 轨迹管理

在轨迹管理中，可以进行查看轨迹详情和删除轨迹。

<img src="/_media/TraceList.png"/>

查看轨迹详情：

<img src="/_media/TraceInfo.png"/>

# 电子围栏管理

在电子围栏管理中，可以查看围栏详情和删除围栏。

<img src="/_media/FenceList.png"/>

查看围栏详情（围栏类型有：行政区围栏、多边形围栏、圆形围栏、线形围栏）：

<img src="/_media/FenceInfo1.png"/>

<img src="/_media/FenceInfo2.png"/>

<img src="/_media/FenceInfo3.png"/>

<img src="/_media/FenceInfo4.png"/>