# 关于EagleMap-sdk

EagleMap-sdk是基于EagleMap的restful接口实现的Java SDK的封装，实现了地图基础功能、轨迹服务、轨迹终端、轨迹、电子围栏等功能。EagleMap-sdk的依赖非常少，仅依赖了[hutool](https://www.hutool.cn/)工具包，尽可能的不对业务系统依赖环境产生困扰。

# 快速集成使用

EagleMap-sdk提供了两种方式与业务系统对接，分别是：

- java sdk方式
- Spring Boot集成方式

# 版本说明

> 建议使用稳定版。

| 项目                         | 最新稳定版 | 发布时间   |
| ---------------------------- | ---------- | ---------- |
| EagleMap-sdk                 | 1.0        | 2022-03-31 |
| eaglemap-spring-boot-starter | 1.0        | 2022-03-31 |

目前最新快照版为：1.1-SNAPSHOT

## java sdk方式

> EagleMap-sdk源码：
>
> https://gitee.com/itcastopen/EagleMap-sdk
>
> [https://github.com/itcastopen/EagleMap-sdk](https://github.com/itcastopen/EagleMap-sdk)

> **第一步，导入maven依赖**

~~~xml
<dependency>
    <groupId>com.itheima.em</groupId>
    <artifactId>EagleMap-sdk</artifactId>
    <version>{version}</version>
</dependency>
~~~

> **第二步，实例化EagleMapTemplate对象**

~~~java

String host = "127.0.0.1"; //EagleMap服务地址
int port = 8484; //EagleMap服务端口
int timeout = 1000; //http请求的超时时间

//实例化EagleMapTemplate对象
EagleMapTemplate eagleMapTemplate = new EagleMapTemplate(host, port, timeout);

//后续基于eagleMapTemplate可以调用EagleMap的各种服务
String ip = "114.242.26.45";
//根据ip地址查询对应的地区、经纬度坐标等信息
IpResult ipResult = eagleMapTemplate.opsForBase().queryIp(ip);
System.out.println(ipResult);
~~~

## Spring Boot集成方式

> eaglemap-spring-boot-starter源码：
>
> https://gitee.com/itcastopen/eaglemap-spring-boot-starter.git
>
> https://github.com/itcastopen/eaglemap-spring-boot-starter.git



> **第一步，导入maven依赖**

~~~xml
<dependency>
    <groupId>com.itheima.em</groupId>
    <artifactId>eaglemap-spring-boot-starter</artifactId>
    <version>{version}</version>
</dependency>

<!-- 如果是SNAPSHOT版本，如要在项目的pom.xml文件中引入快照版源 -->
<repositories>
    <repository>
        <id>sonatypeSnapshots</id>
        <name>Sonatype Snapshots</name>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
    </repository>
</repositories>
~~~

> **第二步，配置application.yml文件**

~~~yml
eagle:
  host: 127.0.0.1 #EagleMap服务地址
  port: 8484 #EagleMap服务端口
  timeout: 10000 #http请求的超时时间
~~~

**第三步，测试用例**

~~~java
package com.itheima.em.boot;

import EagleMapTemplate;
import IpResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EagleMapTest {

    @Autowired //自动注入
    private EagleMapTemplate eagleMapTemplate;

    @Test
    public void queryIp() {
        String ip = "114.242.26.45";
        //根据ip地址查询对应的地区、经纬度坐标等信息
        IpResult ipResult = this.eagleMapTemplate.opsForBase().queryIp(ip);
        System.out.println(ipResult);
    }

}
~~~

# SDK的用法

EagleMap-sdk统一通过`EagleMapTemplate`类的`opsForXxx()`方法进行操作，主要分为基础功能、路线规划、轨迹服务、轨迹终端、轨迹管理、电子围栏操作。

!> 无论使用百度地图还是高德地图，EagleMap中使用的坐标体系统一为：`gcj02`。

## 基础功能

基础功能中包含如下功能：

- IP定位
- 查询静态地图
- 坐标转化
- 详细地址转换坐标
- 坐标转化详细地址

使用示例：

~~~java
package com.itheima.em.sdk.ops;

import com.itheima.em.sdk.EagleMapTemplate;
import com.itheima.em.sdk.enums.CoordinateEnum;
import com.itheima.em.sdk.enums.ProviderEnum;
import com.itheima.em.sdk.vo.Coordinate;
import com.itheima.em.sdk.vo.GeoResult;
import com.itheima.em.sdk.vo.IpResult;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BaseOperationsTest {

    private EagleMapTemplate eagleMapTemplate;

    @Before
    public void init() { //初始化方法
        String host = "127.0.0.1";
        this.eagleMapTemplate = new EagleMapTemplate(host);
    }

    @Test
    public void queryIp() { //IP定位
        String ip = "115.47.143.145";
        IpResult ipResult = this.eagleMapTemplate
                .opsForBase().queryIp(ip);
        System.out.println(ipResult);
    }

    @Test
    public void staticMapImage() { //查询静态地图
        Double longitude = 116.405285;
        Double latitude = 39.904989;
        // String result = this.eagleMapTemplate.opsForBase().staticMapImage(longitude, latitude);
        String result = this.eagleMapTemplate.opsForBase().staticMapImage(ProviderEnum.BAIDU, longitude, latitude);
        System.out.println(result);
    }

    @Test
    public void convertToGcj02() { //坐标转化，将坐标转为gcj02
        Coordinate[] vos = new Coordinate[]{new Coordinate(116.350717, 40.066273),
                new Coordinate(116.336429, 40.072473)};
        List<Coordinate> coordinates = this.eagleMapTemplate.opsForBase().convertToGcj02(ProviderEnum.BAIDU, CoordinateEnum.BAIDU, vos);
        // List<CoordinateVo> coordinateVos = this.eagleMapTemplate.opsForBase().baiduConvertToGcj02(vos);
        for (Coordinate coordinate : coordinates) {
            System.out.println(coordinate.toParam());
        }
    }

    @Test
    public void convert() { //坐标转化，指定坐标类型进行转化
        Coordinate vo = new Coordinate(116.350717, 40.066273);
        Coordinate convert = this.eagleMapTemplate.opsForBase()
                .convert(ProviderEnum.BAIDU, CoordinateEnum.BAIDU, CoordinateEnum.AMAP, vo);
        System.out.println(convert.toParam());

        Coordinate vo2 = new Coordinate(116.34337, 40.060448);
        Coordinate convert2 = this.eagleMapTemplate.opsForBase()
                .convert(ProviderEnum.BAIDU, CoordinateEnum.AMAP, CoordinateEnum.BAIDU, vo2);
        System.out.println(convert2.toParam());
    }

    @Test
    public void bd09ToGcj02() { //坐标转化，百度转化为gcj02
        Coordinate coordinate = this.eagleMapTemplate.opsForBase()
                .bd09ToGcj02(116.350717, 40.066273);
        System.out.println(coordinate);
        //116.343847,40.060539
    }

    @Test
    public void geoCode() { //详细地址转换坐标
        String address = "北京市龙旗广场";
        GeoResult geoResult = this.eagleMapTemplate.opsForBase()
                .geoCode(ProviderEnum.AMAP, address, null);
        System.out.println(geoResult);
    }

    @Test
    public void geoDecode() { //坐标转化详细地址
        GeoResult geoResult = this.eagleMapTemplate.opsForBase()
                .geoDecode(ProviderEnum.BAIDU, 116.346566,40.066852, null);
        System.out.println(geoResult);
    }

}
~~~

## 路线规划

路线规划中包含如下功能：

- 驾车路径规划
- 步行路线规划
- 骑行路线规划

使用示例：

~~~java
package com.itheima.em.sdk.ops;

import com.itheima.em.sdk.EagleMapTemplate;
import com.itheima.em.sdk.enums.ProviderEnum;
import com.itheima.em.sdk.vo.Coordinate;
import org.junit.Before;
import org.junit.Test;

public class DirectionOperationsTest {

    private EagleMapTemplate eagleMapTemplate;

    @Before
    public void init() {
        String host = "127.0.0.1";
        this.eagleMapTemplate = new EagleMapTemplate(host);
    }

    //驾车规划
    @Test
    public void driving() {
        Coordinate origin = new Coordinate(116.34411597643727, 40.06061915065967);
        Coordinate destination = new Coordinate(116.398704, 39.907539);
        String result = this.eagleMapTemplate.opsForDirection().driving(ProviderEnum.AMAP, origin, destination);
        System.out.println(result);
    }

    //步行路线规划
    @Test
    public void walking() {
        Coordinate origin = new Coordinate(116.34411597643727, 40.06061915065967);
        Coordinate destination = new Coordinate(116.398704, 39.907539);
        String result = this.eagleMapTemplate.opsForDirection().walking(ProviderEnum.AMAP, origin, destination);
        System.out.println(result);
    }

    //骑行路线规划
    @Test
    public void bicycling() {
        Coordinate origin = new Coordinate(116.34411597643727, 40.06061915065967);
        Coordinate destination = new Coordinate(116.398704, 39.907539);
        String result = this.eagleMapTemplate.opsForDirection().bicycling(ProviderEnum.AMAP, origin, destination);
        System.out.println(result);
    }
}
~~~

## 轨迹服务

轨迹服务中包含如下功能：

- 创建轨迹服务
- 删除轨迹服务
- 更新轨迹服务
- 查询单个轨迹服务
- 查询所有的轨迹服务

使用示例：

~~~java
package com.itheima.em.sdk.ops;

import com.itheima.em.sdk.EagleMapTemplate;
import com.itheima.em.sdk.enums.ProviderEnum;
import com.itheima.em.sdk.vo.TraceServer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TraceServerOperationsTest {


    private EagleMapTemplate eagleMapTemplate;

    @Before
    public void init() {
        String host = "127.0.0.1";
        this.eagleMapTemplate = new EagleMapTemplate(host);
    }

    @Test
    public void create() { //创建轨迹服务
        Long serverId = this.eagleMapTemplate.opsForTraceServer().create("测试轨迹1", "测试轨迹描述1");
        System.out.println(serverId);
    }

    @Test
    public void delete() { //删除轨迹服务
        Boolean result = this.eagleMapTemplate.opsForTraceServer()
                .delete(634598L);
        System.out.println(result);
    }

    @Test
    public void update() { //更新轨迹服务
        Boolean result = this.eagleMapTemplate.opsForTraceServer()
                .update(634598L, "测试轨迹11", "测试轨迹描述11");
        System.out.println(result);
    }

    @Test
    public void queryById() { //查询单个轨迹服务
        TraceServer traceServer = this.eagleMapTemplate.opsForTraceServer().queryById(634598L);
        System.out.println(traceServer);
    }

    @Test
    public void queryAll() { //查询所有的轨迹服务
        List<TraceServer> traceServerList =
                this.eagleMapTemplate.opsForTraceServer().queryAll(ProviderEnum.AMAP);
        traceServerList.forEach(traceServer -> System.out.println(traceServer));
    }
}
~~~

## 轨迹终端

轨迹终端中包含如下功能：

- 创建终端
- 删除终端
- 更新终端
- 查询终端列表
- 查询终端在某个轨迹中的最新位置

使用示例：

~~~java
package com.itheima.em.sdk.ops;

import com.itheima.em.sdk.EagleMapTemplate;
import com.itheima.em.sdk.enums.ProviderEnum;
import com.itheima.em.sdk.vo.PageResult;
import com.itheima.em.sdk.vo.TraceTerminal;
import org.junit.Before;
import org.junit.Test;

public class TraceTerminalOperationsTest {

    private EagleMapTemplate eagleMapTemplate;

    @Before
    public void init() {
        String host = "127.0.0.1";
        this.eagleMapTemplate = new EagleMapTemplate(host);
    }

    @Test
    public void create() { //创建终端
        Long id = this.eagleMapTemplate.opsForTraceTerminal()
                .create(ProviderEnum.BAIDU, 231526L, "测试终端1", "测试终端描述1", null);
        System.out.println(id);
    }

    @Test
    public void delete() { //删除终端
        Boolean result = this.eagleMapTemplate.opsForTraceTerminal()
                .delete(ProviderEnum.BAIDU, 231526L, 1503942717096951808L);
        System.out.println(result);
    }

    @Test
    public void update() { //更新终端
        //百度地图不支持修改终端名称
        Boolean result = this.eagleMapTemplate.opsForTraceTerminal()
                .update(ProviderEnum.BAIDU, 231526L, 1503942717096951808L, "测试终端111", "测试终端描述111", null);
        System.out.println(result);
    }

    @Test
    public void queryList() { //查询终端列表
        PageResult<TraceTerminal> pageResult = this.eagleMapTemplate.opsForTraceTerminal()
                .queryList(ProviderEnum.BAIDU, 231526L, 1, 20);
        System.out.println(pageResult);
    }

    @Test
    public void queryLastPointAmap() { //查询终端在某个轨迹中的最新位置
        Long serverId = 617418L;
        Long terminalId = 492666422L;
        Long traceId = 260L;
        String data = this.eagleMapTemplate.opsForTraceTerminal()
                .queryLastPoint(ProviderEnum.AMAP, serverId, terminalId, traceId);
        System.out.println(data);
    }

    @Test
    public void queryLastPointBaidu() { //查询终端在某个轨迹中的最新位置
        Long serverId = 231517L;
        Long terminalId = 1498562156533092352L;
        Long traceId = 1498572699788587008L;
        String data = this.eagleMapTemplate.opsForTraceTerminal()
                .queryLastPoint(ProviderEnum.BAIDU, serverId, terminalId, traceId);
        System.out.println(data);
    }
}
~~~

## 轨迹管理

轨迹管理中包含如下功能：

- 创建轨迹
- 删除轨迹
- 上传轨迹点
- 停止轨迹
  - 该方法会将轨迹中的轨迹点数据持久化本地数据库中
- 分页查询轨迹列表
- 查询轨迹详情
- 查询轨迹缩略图

使用示例：

~~~java
package com.itheima.em.sdk.ops;

import cn.hutool.core.map.MapUtil;
import com.itheima.em.sdk.EagleMapTemplate;
import com.itheima.em.sdk.enums.ProviderEnum;
import com.itheima.em.sdk.vo.PageResult;
import com.itheima.em.sdk.vo.Trace;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraceOperationsTest {


    private EagleMapTemplate eagleMapTemplate;

    @Before
    public void init() {
        String host = "127.0.0.1";
        this.eagleMapTemplate = new EagleMapTemplate(host);
    }

    @Test
    public void create() { //创建轨迹
        Long serverId = 617418L;
        Long terminalId = 492666422L;

        Long traceId = this.eagleMapTemplate.opsForTrace()
                .create(ProviderEnum.AMAP, serverId, terminalId, "trace_" + System.currentTimeMillis());

        System.out.println(traceId);
    }

    @Test
    public void delete() { //删除轨迹
        Long serverId = 617418L;
        Long terminalId = 492666422L;
        Long traceId = 260L;
        Boolean result = this.eagleMapTemplate.opsForTrace()
                .delete(serverId, terminalId, traceId);
        System.out.println(result);
    }

    @Test
    public void upload() { //上传轨迹点
        Long serverId = 617418L;
        Long terminalId = 492666422L;
        Long traceId = 260L;

        List<Map<String, Object>> pointList = new ArrayList<>();
        long time = System.currentTimeMillis();
        pointList.add(MapUtil.builder(new HashMap<String, Object>())
                .put("location", "116.343982,40.060585")
                .put("locatetime", time - 50000)
                .put("speed", "")
                .build());

        pointList.add(MapUtil.builder(new HashMap<String, Object>())
                .put("location", "116.343961,40.059769")
                .put("locatetime", time - 40000)
                .put("speed", "")
                .build());

        pointList.add(MapUtil.builder(new HashMap<String, Object>())
                .put("location", "116.341879,40.059392")
                .put("locatetime", time - 30000)
                .put("speed", "")
                .build());

        pointList.add(MapUtil.builder(new HashMap<String, Object>())
                .put("location", "116.340377,40.059145")
                .put("locatetime", time - 30000)
                .put("speed", "")
                .build());

        pointList.add(MapUtil.builder(new HashMap<String, Object>())
                .put("location", "116.340077,40.060705")
                .put("locatetime", time - 20000)
                .put("speed", "")
                .build());

        pointList.add(MapUtil.builder(new HashMap<String, Object>())
                .put("location", "116.344132,40.061937")
                .put("locatetime", time - 10000)
                .put("speed", "")
                .build());

        Boolean result = this.eagleMapTemplate.opsForTrace()
                .upload(ProviderEnum.AMAP, serverId, terminalId, traceId, pointList);
        System.out.println(result);
    }

    @Test
    public void stopTrace() { //停止轨迹
        Boolean result = this.eagleMapTemplate.opsForTrace()
                .stopTrace(617418L, 492731778L, 240L, null);
        System.out.println(result);
    }

    @Test
    public void queryTracePageList() { //分页查询轨迹列表
        PageResult<Trace> tracePageResult = this.eagleMapTemplate.opsForTrace()
                .queryTracePageList(1, 20);
        tracePageResult.getItems()
                .forEach(trace -> System.out.println(trace));
    }

    @Test
    public void queryTraceInfo() { //查询轨迹详情
        Long serverId = 617418L;
        Long terminalId = 492666422L;
        Long traceId = 260L;

        Trace trace = this.eagleMapTemplate.opsForTrace()
                .queryTraceInfo(serverId, terminalId, traceId, null);

        System.out.println(trace);
    }

    @Test
    public void queryTraceImage() { //查询轨迹缩略图
        String image = this.eagleMapTemplate.opsForTrace()
                .queryTraceImage(ProviderEnum.AMAP, 617418L, 492731778L, 240L, null, null, null);
        System.out.println(image);
    }
}
~~~

## 电子围栏

电子围栏中包含如下功能：

- 创建圆形电子围栏
- 创建多边形围栏
- 创建线形围栏
- 创建行政区划围栏
- 更新圆形围栏
- 更新多边形围栏
- 更新线形围栏
- 更新行政区划围栏
- 删除围栏
- 绑定终端到电子围栏中
- 解绑电子围栏中的终端
- 分页查询围栏中的终端列表
- 分页查询围栏列表
- 根据围栏id查询围栏信息
- 查询终端在围栏中的状态

使用示例：

~~~java
package com.itheima.em.sdk.ops;

import com.itheima.em.sdk.EagleMapTemplate;
import com.itheima.em.sdk.enums.ProviderEnum;
import com.itheima.em.sdk.vo.PageResult;
import com.itheima.em.sdk.vo.TraceFence;
import com.itheima.em.sdk.vo.TraceTerminal;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TraceFenceOperationsTest {


    private EagleMapTemplate eagleMapTemplate;

    @Before
    public void init() {
        String host = "127.0.0.1";
        this.eagleMapTemplate = new EagleMapTemplate(host);
    }

    //高德圆形围栏
    @Test
    public void createCircleFenceAmap() {
        Long serverId = 617418L;
        String name = "测试电子围栏1";
        String desc = "测试电子围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("center", "116.344132,40.061937"); //中心点
        param.put("radius", 5000); //半径，单位：米
        Long fenceId = this.eagleMapTemplate.opsForTraceFence()
                .createCircleFence(ProviderEnum.AMAP, serverId, name, desc, param);
        System.out.println(fenceId);
    }

    //百度圆形围栏
    @Test
    public void createCircleFenceBaidu() {
        Long serverId = 231526L;
        String name = "测试电子围栏2";
        String desc = "测试电子围栏2的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("longitude", "116.344132"); //中心点
        param.put("latitude", "40.061937"); //中心点
        param.put("radius", 5000); //半径，单位：米
        Long fenceId = this.eagleMapTemplate.opsForTraceFence()
                .createCircleFence(ProviderEnum.BAIDU, serverId, name, desc, param);
        System.out.println(fenceId);
    }

    //高德多边形围栏
    @Test
    public void createPolygonFenceAmap() {
        Long serverId = 617418L;
        String name = "高德多边形围栏1";
        String desc = "高德多边形围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("points", "116.341622,40.063412;116.346729,40.064546;116.347072,40.060358;116.342373,40.059553"); //多边形的点
        Long fenceId = this.eagleMapTemplate.opsForTraceFence()
                .createPolygonFence(ProviderEnum.AMAP, serverId, name, desc, param);
        System.out.println(fenceId);
    }

    //百度多边形围栏
    @Test
    public void createPolygonFenceBaidu() {
        Long serverId = 231526L;
        String name = "百度多边形围栏1";
        String desc = "百度多边形围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("vertexes", "40.063412,116.341622;40.064546,116.346729;40.060358,116.347072;40.059553,116.342373"); //多边形的点
        Long fenceId = this.eagleMapTemplate.opsForTraceFence()
                .createPolygonFence(ProviderEnum.BAIDU, serverId, name, desc, param);
        System.out.println(fenceId);
    }

    //高德线形围栏
    @Test
    public void createPolylineFenceAmap() {
        Long serverId = 617418L;
        String name = "高德线形围栏1";
        String desc = "高德线形围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("points", "116.347115,40.060325;116.342909,40.05957;116.336493,40.058338"); //线形点
        param.put("bufferradius", 200);//沿线偏移距离，单位：米
        Long fenceId = this.eagleMapTemplate.opsForTraceFence()
                .createPolylineFence(ProviderEnum.AMAP, serverId, name, desc, param);
        System.out.println(fenceId);
    }

    //百度线形围栏
    @Test
    public void createPolylineFenceBaidu() {
        Long serverId = 231526L;
        String name = "百度线形围栏1";
        String desc = "百度线形围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("vertexes", "40.060325,116.347115;40.05957,116.342909;40.058338,116.336493"); //线形点
        param.put("offset", 200);//沿线偏移距离，单位：米
        Long fenceId = this.eagleMapTemplate.opsForTraceFence()
                .createPolylineFence(ProviderEnum.BAIDU, serverId, name, desc, param);
        System.out.println(fenceId);
    }

    //高德行政区围栏
    @Test
    public void createDistrictFenceAmap() {
        Long serverId = 617418L;
        String name = "高德行政区围栏1";
        String desc = "高德行政区围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("adcode", 110105); //行政区划编码
        Long fenceId = this.eagleMapTemplate.opsForTraceFence()
                .createDistrictFence(ProviderEnum.AMAP, serverId, name, desc, param);
        System.out.println(fenceId);
    }

    //百度行政区围栏
    @Test
    public void createDistrictFenceBaidu() {
        Long serverId = 231526L;
        String name = "百度行政区围栏1";
        String desc = "百度行政区围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("keyword", "北京市"); //行政区名称
        Long fenceId = this.eagleMapTemplate.opsForTraceFence()
                .createDistrictFence(ProviderEnum.BAIDU, serverId, name, desc, param);
        System.out.println(fenceId);
    }

    //更新高德圆形围栏
    @Test
    public void updateCircleFenceAmap() {
        Long serverId = 617418L;
        Long fenceId = 589280L;
        String name = "测试电子围栏1";
        String desc = "测试电子围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("center", "116.344132,40.061937"); //中心点
        param.put("radius", 5000); //半径，单位：米
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .updateCircleFence(ProviderEnum.AMAP, serverId, fenceId, name, desc, param);
        System.out.println(result);
    }

    //更新百度圆形围栏
    @Test
    public void updateCircleFenceBaidu() {
        Long serverId = 231526L;
        Long fenceId = 7L;
        String name = "测试电子围栏2";
        String desc = "测试电子围栏2的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("longitude", "116.344132"); //中心点
        param.put("latitude", "40.061937"); //中心点
        param.put("radius", 5000); //半径，单位：米
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .updateCircleFence(ProviderEnum.BAIDU, serverId, fenceId, name, desc, param);
        System.out.println(result);
    }

    //高德多边形围栏
    @Test
    public void updatePolygonFenceAmap() {
        Long serverId = 617418L;
        Long fenceId = 587079L;
        String name = "高德多边形围栏1";
        String desc = "高德多边形围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("points", "116.341622,40.063412;116.346729,40.064546;116.347072,40.060358;116.342373,40.059553"); //多边形的点
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .updatePolygonFence(ProviderEnum.AMAP, serverId, fenceId, name, desc, param);
        System.out.println(result);
    }

    //百度多边形围栏
    @Test
    public void updatePolygonFenceBaidu() {
        Long serverId = 231526L;
        Long fenceId = 8L;
        String name = "百度多边形围栏1";
        String desc = "百度多边形围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("vertexes", "40.063412,116.341622;40.064546,116.346729;40.060358,116.347072;40.059553,116.342373"); //多边形的点
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .updatePolygonFence(ProviderEnum.BAIDU, serverId, fenceId, name, desc, param);
        System.out.println(result);
    }

    //高德线形围栏
    @Test
    public void updatePolylineFenceAmap() {
        Long serverId = 617418L;
        Long fenceId = 589360L;
        String name = "高德线形围栏1";
        String desc = "高德线形围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("points", "116.347115,40.060325;116.342909,40.05957;116.336493,40.058338"); //线形点
        param.put("bufferradius", 200);//沿线偏移距离，单位：米
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .updatePolylineFence(ProviderEnum.AMAP, serverId, fenceId, name, desc, param);
        System.out.println(result);
    }

    //百度线形围栏
    @Test
    public void updatePolylineFenceBaidu() {
        Long serverId = 231526L;
        Long fenceId = 9L;
        String name = "百度线形围栏1";
        String desc = "百度线形围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("vertexes", "40.060325,116.347115;40.05957,116.342909;40.058338,116.336493"); //线形点
        param.put("offset", 200);//沿线偏移距离，单位：米
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .updatePolylineFence(ProviderEnum.BAIDU, serverId, fenceId, name, desc, param);
        System.out.println(result);
    }

    //高德行政区围栏
    @Test
    public void updateDistrictFenceAmap() {
        Long serverId = 617418L;
        Long fenceId = 589380L;
        String name = "高德行政区围栏1";
        String desc = "高德行政区围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("adcode", 110000); //行政区划编码
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .updateDistrictFence(ProviderEnum.AMAP, serverId, fenceId, name, desc, param);
        System.out.println(result);
    }

    //百度行政区围栏
    @Test
    public void updateDistrictFenceBaidu() {
        Long serverId = 231526L;
        Long fenceId = 10L;
        String name = "百度行政区围栏1";
        String desc = "百度行政区围栏1的描述";
        Map<String, Object> param = new HashMap<>();
        param.put("keyword", "北京市"); //行政区名称
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .updateDistrictFence(ProviderEnum.BAIDU, serverId, fenceId, name, desc, param);
        System.out.println(result);
    }

    //高德删除
    @Test
    public void deleteFenceAmap() {
        Long serverId = 617418L;
        Long fenceId = 588679L;
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .deleteFence(ProviderEnum.AMAP, serverId, fenceId);
        System.out.println(result);
    }

    //百度删除
    @Test
    public void deleteFenceBaidu() {
        Long serverId = 231526L;
        Long fenceId = 10L;
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .deleteFence(ProviderEnum.BAIDU, serverId, fenceId);
        System.out.println(result);
    }

    //将终端加入电子围栏中-高德
    @Test
    public void bindTerminalFenceAmap() {
        Long serverId = 617418L;
        Long fenceId = 589380L;
        Long[] terminalIds = new Long[]{492666422L, 492731778L};
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .bindTerminalFence(ProviderEnum.AMAP, serverId, fenceId, terminalIds);
        System.out.println(result);
    }

    //将终端加入电子围栏中-百度
    @Test
    public void bindTerminalFenceBaidu() {
        Long serverId = 231526L;
        Long fenceId = 11L;
        Long[] terminalIds = new Long[]{1501743673574510592L};
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .bindTerminalFence(ProviderEnum.BAIDU, serverId, fenceId, terminalIds);
        System.out.println(result);
    }

    //解绑电子围栏中的终端-高德
    @Test
    public void unbindTerminalFenceAmap() {
        Long serverId = 617418L;
        Long fenceId = 589380L;
        Long[] terminalIds = new Long[]{492666422L, 492731778L};
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .unbindTerminalFence(ProviderEnum.AMAP, serverId, fenceId, terminalIds);
        System.out.println(result);
    }

    //解绑电子围栏中的终端-百度
    @Test
    public void unbindTerminalFenceBaidu() {
        Long serverId = 231526L;
        Long fenceId = 11L;
        Long[] terminalIds = new Long[]{1501743673574510592L};
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .unbindTerminalFence(ProviderEnum.BAIDU, serverId, fenceId, terminalIds);
        System.out.println(result);
    }

    //查询围栏中的终端 - 高德
    @Test
    public void queryTerminalFenceListAmap() {
        Long serverId = 617418L;
        Long fenceId = 589380L;
        PageResult<TraceTerminal> pageResult = this.eagleMapTemplate.opsForTraceFence()
                .queryTerminalFenceList(ProviderEnum.AMAP, serverId, fenceId, 1, 10);
        System.out.println(pageResult);
    }

    //查询围栏中的终端 - 百度
    @Test
    public void queryTerminalFenceListBaidu() {
        Long serverId = 231526L;
        Long fenceId = 11L;
        PageResult<TraceTerminal> pageResult = this.eagleMapTemplate.opsForTraceFence()
                .queryTerminalFenceList(ProviderEnum.BAIDU, serverId, fenceId, 1, 10);
        System.out.println(pageResult);
    }

    @Test
    public void queryFenceList() {  //分页查询围栏列表
        PageResult<TraceFence> pageResult = this.eagleMapTemplate.opsForTraceFence()
                .queryFenceList(ProviderEnum.BAIDU, 1, 2);
        System.out.println(pageResult);
        System.out.println("-----------");

        PageResult<TraceFence> pageResult2 = this.eagleMapTemplate.opsForTraceFence()
                .queryFenceList(ProviderEnum.AMAP, 1, 2);
        System.out.println(pageResult2);
    }

    @Test
    public void queryByFenceIdAmap() { //根据围栏id查询围栏信息
        Long serverId = 617418L;
        Long fenceId = 589380L;
        TraceFence traceFence = this.eagleMapTemplate.opsForTraceFence()
                .queryByFenceId(ProviderEnum.AMAP, serverId, fenceId);
        System.out.println(traceFence);
    }

    @Test
    public void queryByFenceIdBaidu() { //根据围栏id查询围栏信息
        Long serverId = 231526L;
        Long fenceId = 11L;
        TraceFence traceFence = this.eagleMapTemplate.opsForTraceFence()
                .queryByFenceId(ProviderEnum.BAIDU, serverId, fenceId);
        System.out.println(traceFence);
    }

    @Test
    public void queryTerminalStatus() { //查询终端在围栏中的状态
        Long serverId = 617418L;
        Long fenceId = 589380L;
        Long terminalId = 492666422L;
        Boolean result = this.eagleMapTemplate.opsForTraceFence()
                .queryTerminalStatus(ProviderEnum.AMAP, serverId, fenceId, terminalId);
        System.out.println(result);
    }
}
~~~