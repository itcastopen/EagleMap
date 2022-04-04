# RESTful文档
> 如果是Java语言开发的系统建议通过[EagleMap-sdk](/zh-cn/sdk)使用服务。

# 地图基础服务
## 指定坐标体系转化
**接口地址**:`/api/coordinate/convert`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>可以通过指定坐标类型，将原坐标转化为目标坐标，例如：将百度地图的坐标转化为高德的坐标</p>
**请求示例**:
```javascript
{
  "coordinate": {
    "latitude": 0,
    "longitude": 0
  },
  "fromType": "BAIDU",
  "provider": "NONE",
  "toType": "AMAP"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|convertParam|convertParam|body|true|ConvertParam|ConvertParam|
|&emsp;&emsp;coordinate|原坐标值||true|CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;fromType|原坐标类型，可用参数（必须大写）：BAIDU、AMAP、QQ、GPS、SOU_GOU、EAGLE||true|string||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：百度地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;toType|目标坐标类型，可用参数（必须大写）：BAIDU、AMAP、QQ、GPS、SOU_GOU、EAGLE||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«CoordinateVo»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|CoordinateVo|CoordinateVo|
|&emsp;&emsp;latitude|纬度|number||
|&emsp;&emsp;longitude|经度|number||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"latitude": 0,
		"longitude": 0
	},
	"msg": ""
}
```


## 转化为gcj02坐标体系


**接口地址**:`/api/coordinate/convert/gcj02`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>将坐标转化为gcj02，该方法不能指定目标类型。</p>



**请求示例**:


```javascript
{
  "coordinates": [
    {
      "latitude": 0,
      "longitude": 0
    }
  ],
  "fromType": "BAIDU",
  "provider": "NONE"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|param|param|body|true|ConvertToGcj02Param|ConvertToGcj02Param|
|&emsp;&emsp;coordinates|坐标数据||true|array|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;fromType|原坐标类型，可用参数（必须大写）：BAIDU、AMAP、QQ、GPS、SOU_GOU、EAGLE||true|string||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：百度地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«List«CoordinateVo»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|array|CoordinateVo|
|&emsp;&emsp;latitude|纬度|number||
|&emsp;&emsp;longitude|经度|number||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"latitude": 0,
			"longitude": 0
		}
	],
	"msg": ""
}
```


## 地理编码


**接口地址**:`/api/geo/code`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>将详细的结构化地址转换为经纬度坐标，例如：北京市昌平区回龙观街道传智播客办公楼</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|address|详细地址|query|true|string||
|param|可选参数，具体参考服务商文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding<br/>高德地图：https://lbs.amap.com/api/webservice/guide/api/georegeo#geo|query|false|string||
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«GeoResultVO»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|GeoResultVO|GeoResultVO|
|&emsp;&emsp;address||string||
|&emsp;&emsp;city||string||
|&emsp;&emsp;country||string||
|&emsp;&emsp;data||string||
|&emsp;&emsp;district||string||
|&emsp;&emsp;location||CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度|number||
|&emsp;&emsp;province||string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"address": "",
		"city": "",
		"country": "",
		"data": "",
		"district": "",
		"location": {
			"latitude": 0,
			"longitude": 0
		},
		"province": ""
	},
	"msg": ""
}
```


## 逆地理编码


**接口地址**:`/api/geo/decode`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>将经纬度转换为详细结构化的地址，且返回附近周边的POI、AOI信息。例如：116.343847,40.060539</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|latitude|维度|query|true|string||
|longitude|经度|query|true|string||
|param|可选参数，具体参考服务商文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding<br/>高德地图：https://lbs.amap.com/api/webservice/guide/api/georegeo#regeo|query|false|string||
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«GeoResultVO»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|GeoResultVO|GeoResultVO|
|&emsp;&emsp;address||string||
|&emsp;&emsp;city||string||
|&emsp;&emsp;country||string||
|&emsp;&emsp;data||string||
|&emsp;&emsp;district||string||
|&emsp;&emsp;location||CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度|number||
|&emsp;&emsp;province||string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"address": "",
		"city": "",
		"country": "",
		"data": "",
		"district": "",
		"location": {
			"latitude": 0,
			"longitude": 0
		},
		"province": ""
	},
	"msg": ""
}
```


## IP定位


**接口地址**:`/api/ip`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>IP定位是一套简单的HTTP接口，根据用户输入的IP地址，能够快速的帮用户定位IP的所在位置。</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ip|IP地址，如：114.242.26.45|query|true|string||
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|false|string||
|type|IP类型,值为 4 或 6，4 表示 IPv4，6 表示 IPv6|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«IpResultVo»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|IpResultVo|IpResultVo|
|&emsp;&emsp;city|城市|string||
|&emsp;&emsp;country|国家（或地区）|string||
|&emsp;&emsp;district|区县|string||
|&emsp;&emsp;ip|IP地址|string||
|&emsp;&emsp;isp|运营商,如电信、联通、移动|string||
|&emsp;&emsp;location|经纬度|string||
|&emsp;&emsp;province|省份|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"city": "",
		"country": "",
		"district": "",
		"ip": "",
		"isp": "",
		"location": "",
		"province": ""
	},
	"msg": ""
}
```


## 查询地图静态图


**接口地址**:`/api/static/map`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>根据参数查询地图的静态图，响应图片的base64数据。</p>



**请求示例**:


```javascript
{
  "height": 300,
  "location": {
    "latitude": 0,
    "longitude": 0
  },
  "param": {},
  "provider": "NONE",
  "width": 750,
  "zoom": 10
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|staticMapParam|staticMapParam|body|true|StaticMapParam|StaticMapParam|
|&emsp;&emsp;height|图片的宽度，默认：300||false|integer||
|&emsp;&emsp;location|位置坐标||true|CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;param|百度/高德的可选参数，如需要请根据官方文档添加参数<br/>百度：https://lbsyun.baidu.com/index.php?title=static<br/>高德：https://lbs.amap.com/api/webservice/guide/api/staticmaps||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;width|图片的宽度，默认：750||false|integer||
|&emsp;&emsp;zoom|地图缩放比，默认：10||false|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


# 地图轨迹管理


## 创建轨迹


**接口地址**:`/api/trace`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>创建轨迹，百度地图是没有轨迹概念的，EagleMap对此做了兼容，自己维护轨迹数据与高德地图保持一致的操作。<br/>高德地图文档：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track-sdk#t2">https://lbs.amap.com/api/track/lieying-kaifa/api/track-sdk#t2</a></p>



**请求示例**:


```javascript
{
  "name": "",
  "param": {},
  "pointList": [
    {}
  ],
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "traceId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceParam|traceParam|body|true|TraceParam|TraceParam|
|&emsp;&emsp;name|轨迹名称||true|string||
|&emsp;&emsp;param|其它参数，具体参考百度地图或高德地图文档||false|object||
|&emsp;&emsp;pointList|轨迹点列表，只有在上报轨迹 和 查询轨迹时才需要传递||false|array|Map«string,object»|
|&emsp;&emsp;&emsp;&emsp;additionalProperty1|||false|Map«string,object»|Map«string,object»|
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||true|integer||
|&emsp;&emsp;traceId|轨迹id，创建时无需传递||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 删除轨迹


**接口地址**:`/api/trace`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除轨迹，必须的参数有：serverId、terminalId、traceId</p>



**请求示例**:


```javascript
{
  "name": "",
  "param": {},
  "pointList": [
    {}
  ],
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "traceId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceParam|traceParam|body|true|TraceParam|TraceParam|
|&emsp;&emsp;name|轨迹名称||true|string||
|&emsp;&emsp;param|其它参数，具体参考百度地图或高德地图文档||false|object||
|&emsp;&emsp;pointList|轨迹点列表，只有在上报轨迹 和 查询轨迹时才需要传递||false|array|Map«string,object»|
|&emsp;&emsp;&emsp;&emsp;additionalProperty1|||false|Map«string,object»|Map«string,object»|
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||true|integer||
|&emsp;&emsp;traceId|轨迹id，创建时无需传递||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 查询轨迹缩略图


**接口地址**:`/api/trace/image`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>轨迹缩略图首先查询轨迹详情，再根据详情中的轨迹点生成缩略图，优先查询本地数据库，如本地库中没有数据，再查询地图服务商，可以通过param参数中的local参数进行控制，默认为true，如果不希望查询本地库，就将其设置为false</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|true|string||
|serverId|服务id|query|true|string||
|terminalId|终端id|query|true|string||
|traceId|轨迹id|query|true|string||
|height|图片高度，默认：300|query|false|string||
|param|参数，json格式|query|false|string||
|width|图片宽度，默认：300|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 查询轨迹详情


**接口地址**:`/api/trace/info`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>查询轨迹详情，必须的参数有：serverId、terminalId、traceId，可以通过param参数指定其他参数，具体可参考官方文档。</p>



**请求示例**:


```javascript
{
  "name": "",
  "param": {},
  "pointList": [
    {}
  ],
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "traceId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceParam|traceParam|body|true|TraceParam|TraceParam|
|&emsp;&emsp;name|轨迹名称||true|string||
|&emsp;&emsp;param|其它参数，具体参考百度地图或高德地图文档||false|object||
|&emsp;&emsp;pointList|轨迹点列表，只有在上报轨迹 和 查询轨迹时才需要传递||false|array|Map«string,object»|
|&emsp;&emsp;&emsp;&emsp;additionalProperty1|||false|Map«string,object»|Map«string,object»|
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||true|integer||
|&emsp;&emsp;traceId|轨迹id，创建时无需传递||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«Trace»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|Trace|Trace|
|&emsp;&emsp;created||string||
|&emsp;&emsp;distance||number||
|&emsp;&emsp;endPoint||string||
|&emsp;&emsp;endTime||string||
|&emsp;&emsp;id||integer||
|&emsp;&emsp;name||string||
|&emsp;&emsp;pointList||string||
|&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;serverId||integer||
|&emsp;&emsp;size||integer||
|&emsp;&emsp;startPoint||string||
|&emsp;&emsp;startTime||string||
|&emsp;&emsp;status||integer||
|&emsp;&emsp;terminalId||integer||
|&emsp;&emsp;time||integer||
|&emsp;&emsp;traceId||integer||
|&emsp;&emsp;updated||string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"created": "",
		"distance": 0,
		"endPoint": "",
		"endTime": "",
		"id": 0,
		"name": "",
		"pointList": "",
		"provider": "",
		"serverId": 0,
		"size": 0,
		"startPoint": "",
		"startTime": "",
		"status": 0,
		"terminalId": 0,
		"time": 0,
		"traceId": 0,
		"updated": ""
	},
	"msg": ""
}
```


## 查询轨迹列表


**接口地址**:`/api/trace/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页查询轨迹列表，按照轨迹创建时间倒序排序。</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|false|integer(int32)||
|pageSize|pageSize|query|false|integer(int32)||
|provider|provider,可用值:BAIDU,AMAP,QQ,NONE|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«PageResult«Trace»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|PageResult«Trace»|PageResult«Trace»|
|&emsp;&emsp;items|数据列表|array|Trace|
|&emsp;&emsp;&emsp;&emsp;created||string||
|&emsp;&emsp;&emsp;&emsp;distance||number||
|&emsp;&emsp;&emsp;&emsp;endPoint||string||
|&emsp;&emsp;&emsp;&emsp;endTime||string||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;pointList||string||
|&emsp;&emsp;&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;&emsp;&emsp;serverId||integer||
|&emsp;&emsp;&emsp;&emsp;size||integer||
|&emsp;&emsp;&emsp;&emsp;startPoint||string||
|&emsp;&emsp;&emsp;&emsp;startTime||string||
|&emsp;&emsp;&emsp;&emsp;status||integer||
|&emsp;&emsp;&emsp;&emsp;terminalId||integer||
|&emsp;&emsp;&emsp;&emsp;time||integer||
|&emsp;&emsp;&emsp;&emsp;traceId||integer||
|&emsp;&emsp;&emsp;&emsp;updated||string||
|&emsp;&emsp;page|当前页码|integer||
|&emsp;&emsp;pageCount|总页数|integer||
|&emsp;&emsp;pageSize|页面大小|integer||
|&emsp;&emsp;total|总记录数|integer||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"items": [
			{
				"created": "",
				"distance": 0,
				"endPoint": "",
				"endTime": "",
				"id": 0,
				"name": "",
				"pointList": "",
				"provider": "",
				"serverId": 0,
				"size": 0,
				"startPoint": "",
				"startTime": "",
				"status": 0,
				"terminalId": 0,
				"time": 0,
				"traceId": 0,
				"updated": ""
			}
		],
		"page": 0,
		"pageCount": 0,
		"pageSize": 0,
		"total": 0
	},
	"msg": ""
}
```


## 查询轨迹列表


**接口地址**:`/api/trace/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页查询轨迹列表，按照轨迹创建时间倒序排序。</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|provider|provider,可用值:BAIDU,AMAP,QQ,NONE|query|false|string||
|traceId|traceId|query|false|integer(int64)||
|traceName|traceName|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«List«Trace»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|array|Trace|
|&emsp;&emsp;created||string||
|&emsp;&emsp;distance||number||
|&emsp;&emsp;endPoint||string||
|&emsp;&emsp;endTime||string||
|&emsp;&emsp;id||integer||
|&emsp;&emsp;name||string||
|&emsp;&emsp;pointList||string||
|&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;serverId||integer||
|&emsp;&emsp;size||integer||
|&emsp;&emsp;startPoint||string||
|&emsp;&emsp;startTime||string||
|&emsp;&emsp;status||integer||
|&emsp;&emsp;terminalId||integer||
|&emsp;&emsp;time||integer||
|&emsp;&emsp;traceId||integer||
|&emsp;&emsp;updated||string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"created": "",
			"distance": 0,
			"endPoint": "",
			"endTime": "",
			"id": 0,
			"name": "",
			"pointList": "",
			"provider": "",
			"serverId": 0,
			"size": 0,
			"startPoint": "",
			"startTime": "",
			"status": 0,
			"terminalId": 0,
			"time": 0,
			"traceId": 0,
			"updated": ""
		}
	],
	"msg": ""
}
```


## 停止运动


**接口地址**:`/api/trace/stop`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>该操作会将轨迹中的轨迹点数据持久化本地数据库中</p>



**请求示例**:


```javascript
{
  "name": "",
  "param": {},
  "pointList": [
    {}
  ],
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "traceId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceParam|traceParam|body|true|TraceParam|TraceParam|
|&emsp;&emsp;name|轨迹名称||true|string||
|&emsp;&emsp;param|其它参数，具体参考百度地图或高德地图文档||false|object||
|&emsp;&emsp;pointList|轨迹点列表，只有在上报轨迹 和 查询轨迹时才需要传递||false|array|Map«string,object»|
|&emsp;&emsp;&emsp;&emsp;additionalProperty1|||false|Map«string,object»|Map«string,object»|
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||true|integer||
|&emsp;&emsp;traceId|轨迹id，创建时无需传递||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 上报轨迹点


**接口地址**:`/api/trace/upload`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>可以上报多个轨迹点，必须的参数有：serverId、terminalId、traceId、pointList。<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/trackupload#service-page-anchor7">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/trackupload#service-page-anchor7</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track-sdk#t4">https://lbs.amap.com/api/track/lieying-kaifa/api/track-sdk#t4</a></p>



**请求示例**:


```javascript
{
  "name": "",
  "param": {},
  "pointList": [
    {}
  ],
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "traceId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceParam|traceParam|body|true|TraceParam|TraceParam|
|&emsp;&emsp;name|轨迹名称||true|string||
|&emsp;&emsp;param|其它参数，具体参考百度地图或高德地图文档||false|object||
|&emsp;&emsp;pointList|轨迹点列表，只有在上报轨迹 和 查询轨迹时才需要传递||false|array|Map«string,object»|
|&emsp;&emsp;&emsp;&emsp;additionalProperty1|||false|Map«string,object»|Map«string,object»|
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||true|integer||
|&emsp;&emsp;traceId|轨迹id，创建时无需传递||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


# 电子围栏服务


## 查询围栏信息


**接口地址**:`/api/fence`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>必须参数：serverId、fenceId</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceId|围栏id|query|true|string||
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|true|string||
|serverId|服务id|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«TraceFence»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|TraceFence|TraceFence|
|&emsp;&emsp;created||string||
|&emsp;&emsp;desc||string||
|&emsp;&emsp;fenceId||integer||
|&emsp;&emsp;id||integer||
|&emsp;&emsp;name||string||
|&emsp;&emsp;param||string||
|&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;serverId||integer||
|&emsp;&emsp;type|可用值:CIRCLE,POLYGON,POLYLINE,DISTRICT|string||
|&emsp;&emsp;updated||string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"created": "",
		"desc": "",
		"fenceId": 0,
		"id": 0,
		"name": "",
		"param": "",
		"provider": "",
		"serverId": 0,
		"type": "",
		"updated": ""
	},
	"msg": ""
}
```


## 删除围栏


**接口地址**:`/api/fence`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>必须参数：serverId、fenceIds</p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 绑定终端到围栏中


**接口地址**:`/api/fence/bind`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>必须参数：serverId、fenceId、terminalIds</p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 创建圆形围栏


**接口地址**:`/api/fence/circle`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>具体参数参考官方文档：<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence">https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«long»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|integer(int64)|integer(int64)|
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"msg": ""
}
```


## 更新圆形围栏


**接口地址**:`/api/fence/circle`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>具体参数参考官方文档：<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence">https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 创建行政区划围栏


**接口地址**:`/api/fence/district`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>具体参数参考官方文档：<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence">https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«long»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|integer(int64)|integer(int64)|
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"msg": ""
}
```


## 更新行政区划围栏


**接口地址**:`/api/fence/district`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>具体参数参考官方文档：<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence">https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 查询围栏列表


**接口地址**:`/api/fence/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>查询围栏列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|true|string||
|page|页数，默认：1|query|false|string||
|pageSize|页面大小，默认：20|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«PageResult«TraceFence»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|PageResult«TraceFence»|PageResult«TraceFence»|
|&emsp;&emsp;items|数据列表|array|TraceFence|
|&emsp;&emsp;&emsp;&emsp;created||string||
|&emsp;&emsp;&emsp;&emsp;desc||string||
|&emsp;&emsp;&emsp;&emsp;fenceId||integer||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;param||string||
|&emsp;&emsp;&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;&emsp;&emsp;serverId||integer||
|&emsp;&emsp;&emsp;&emsp;type|可用值:CIRCLE,POLYGON,POLYLINE,DISTRICT|string||
|&emsp;&emsp;&emsp;&emsp;updated||string||
|&emsp;&emsp;page|当前页码|integer||
|&emsp;&emsp;pageCount|总页数|integer||
|&emsp;&emsp;pageSize|页面大小|integer||
|&emsp;&emsp;total|总记录数|integer||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"items": [
			{
				"created": "",
				"desc": "",
				"fenceId": 0,
				"id": 0,
				"name": "",
				"param": "",
				"provider": "",
				"serverId": 0,
				"type": "",
				"updated": ""
			}
		],
		"page": 0,
		"pageCount": 0,
		"pageSize": 0,
		"total": 0
	},
	"msg": ""
}
```


## 创建多边形围栏


**接口地址**:`/api/fence/polygon`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>具体参数参考官方文档：<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence">https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«long»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|integer(int64)|integer(int64)|
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"msg": ""
}
```


## 更新多边形围栏


**接口地址**:`/api/fence/polygon`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>具体参数参考官方文档：<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence">https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 创建线形围栏


**接口地址**:`/api/fence/polyline`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>具体参数参考官方文档：<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence">https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«long»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|integer(int64)|integer(int64)|
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"msg": ""
}
```


## 更新线形围栏


**接口地址**:`/api/fence/polyline`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>具体参数参考官方文档：<br/>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence">https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 查询终端在围栏中的状态


**接口地址**:`/api/fence/status`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>查询终端在围栏中的状态，是否超出围栏，必须参数：serverId、fenceId、TerminalId</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceId|围栏id|query|true|string||
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|true|string||
|serverId|服务id|query|true|string||
|terminalId|终端id|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«boolean»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 查询围栏中的终端列表


**接口地址**:`/api/fence/terminal`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>必须参数：serverId、fenceId</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceId|围栏id|query|true|string||
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|true|string||
|serverId|服务id|query|true|string||
|page|页数，默认：1|query|false|string||
|pageSize|页面大小，默认：20|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«PageResult«TraceTerminal»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|PageResult«TraceTerminal»|PageResult«TraceTerminal»|
|&emsp;&emsp;items|数据列表|array|TraceTerminal|
|&emsp;&emsp;&emsp;&emsp;created||string||
|&emsp;&emsp;&emsp;&emsp;desc||string||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;props||string||
|&emsp;&emsp;&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;&emsp;&emsp;serverId||integer||
|&emsp;&emsp;&emsp;&emsp;terminalId||integer||
|&emsp;&emsp;&emsp;&emsp;updated||string||
|&emsp;&emsp;page|当前页码|integer||
|&emsp;&emsp;pageCount|总页数|integer||
|&emsp;&emsp;pageSize|页面大小|integer||
|&emsp;&emsp;total|总记录数|integer||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"items": [
			{
				"created": "",
				"desc": "",
				"id": 0,
				"name": "",
				"props": "",
				"provider": "",
				"serverId": 0,
				"terminalId": 0,
				"updated": ""
			}
		],
		"page": 0,
		"pageCount": 0,
		"pageSize": 0,
		"total": 0
	},
	"msg": ""
}
```


## 解绑围栏中的终端


**接口地址**:`/api/fence/unbind`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>必须参数：serverId、fenceId、terminalIds</p>



**请求示例**:


```javascript
{
  "desc": "",
  "fenceId": 0,
  "fenceIds": [],
  "name": "",
  "page": 0,
  "pageSize": 0,
  "param": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0,
  "terminalIds": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|fenceParam|fenceParam|body|true|FenceParam|FenceParam|
|&emsp;&emsp;desc|围栏描述||true|string||
|&emsp;&emsp;fenceId|围栏id||false|integer||
|&emsp;&emsp;fenceIds|围栏id列表||false|array|integer|
|&emsp;&emsp;name|围栏名称||true|string||
|&emsp;&emsp;page|页数，默认：1||false|integer||
|&emsp;&emsp;pageSize|页面大小，默认：20||false|integer||
|&emsp;&emsp;param|其他请求参数||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id||true|integer||
|&emsp;&emsp;terminalId|终端id||false|integer||
|&emsp;&emsp;terminalIds|终端id列表||false|array|integer|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


# 路线规划


## 骑行路线规划


**接口地址**:`/api/direction/bicycling`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>骑行路径规划用于规划骑行通勤方案，规划时不会考虑路况；考虑天桥、单行线、封路等情况。</p>



**请求示例**:


```javascript
{
  "destination": {
    "latitude": 0,
    "longitude": 0
  },
  "origin": {
    "latitude": 0,
    "longitude": 0
  },
  "param": {},
  "provider": "NONE"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|directionParam|directionParam|body|true|DirectionParam|DirectionParam|
|&emsp;&emsp;destination|终点坐标||true|CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;origin|起点坐标||true|CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;param|请求参数，不同服务商参数不同： <br/>百度地图参考：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-0 <br/> <br/>高德地图参考：https://lbs.amap.com/api/webservice/guide/api/direction#driving||true|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 驾车路线规划


**接口地址**:`/api/direction/driving`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>驾车路径规划 API 可以规划以小客车、轿车通勤出行的方案，并且返回通勤方案的数据。</p>



**请求示例**:


```javascript
{
  "destination": {
    "latitude": 0,
    "longitude": 0
  },
  "origin": {
    "latitude": 0,
    "longitude": 0
  },
  "param": {},
  "provider": "NONE"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|directionParam|directionParam|body|true|DirectionParam|DirectionParam|
|&emsp;&emsp;destination|终点坐标||true|CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;origin|起点坐标||true|CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;param|请求参数，不同服务商参数不同： <br/>百度地图参考：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-0 <br/> <br/>高德地图参考：https://lbs.amap.com/api/webservice/guide/api/direction#driving||true|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 步行路线规划


**接口地址**:`/api/direction/walking`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>步行路径规划 API 可以规划100KM以内的步行通勤方案，并且返回通勤方案的数据。</p>



**请求示例**:


```javascript
{
  "destination": {
    "latitude": 0,
    "longitude": 0
  },
  "origin": {
    "latitude": 0,
    "longitude": 0
  },
  "param": {},
  "provider": "NONE"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|directionParam|directionParam|body|true|DirectionParam|DirectionParam|
|&emsp;&emsp;destination|终点坐标||true|CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;origin|起点坐标||true|CoordinateVo|CoordinateVo|
|&emsp;&emsp;&emsp;&emsp;latitude|纬度||true|number||
|&emsp;&emsp;&emsp;&emsp;longitude|经度||true|number||
|&emsp;&emsp;param|请求参数，不同服务商参数不同： <br/>百度地图参考：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-0 <br/> <br/>高德地图参考：https://lbs.amap.com/api/webservice/guide/api/direction#driving||true|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认为：高德地图,可用值:BAIDU,AMAP,QQ,NONE||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


# 轨迹服务管理


## 查询所有的轨迹服务


**接口地址**:`/api/trace/server`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>查询所有的轨迹服务。</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|provider|provider,可用值:BAIDU,AMAP,QQ,NONE|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«List«TraceServer»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|array|TraceServer|
|&emsp;&emsp;created||string||
|&emsp;&emsp;desc||string||
|&emsp;&emsp;id||integer||
|&emsp;&emsp;name||string||
|&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;serverId||integer||
|&emsp;&emsp;status||boolean||
|&emsp;&emsp;updated||string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"created": "",
			"desc": "",
			"id": 0,
			"name": "",
			"provider": "",
			"serverId": 0,
			"status": true,
			"updated": ""
		}
	],
	"msg": ""
}
```


## 创建轨迹服务


**接口地址**:`/api/trace/server`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>使用轨迹功能必须先创建轨迹服务，百度地图不支持通过接口创建，请通过百度地图的轨迹服务管理系统创建，高德地图支持通过接口创建。<br/>百度地图管理地址：<a href="https://lbsyun.baidu.com/trace/admin/service">https://lbsyun.baidu.com/trace/admin/service</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "name": "",
  "provider": "NONE",
  "serverId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceServerParam|traceServerParam|body|true|TraceServerParam|TraceServerParam|
|&emsp;&emsp;desc|服务描述||false|string||
|&emsp;&emsp;name|服务名称||true|string||
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|服务id，创建时无需传入该参数。||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 更新轨迹服务


**接口地址**:`/api/trace/server`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>百度地图不支持通过接口更新，此方法仅仅是从数据库中更新数据，并不会从百度地图中更新，高德地图支持通过接口更新。<br/>百度地图管理地址：<a href="https://lbsyun.baidu.com/trace/admin/service">https://lbsyun.baidu.com/trace/admin/service</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "name": "",
  "provider": "NONE",
  "serverId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceServerParam|traceServerParam|body|true|TraceServerParam|TraceServerParam|
|&emsp;&emsp;desc|服务描述||false|string||
|&emsp;&emsp;name|服务名称||true|string||
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|服务id，创建时无需传入该参数。||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 删除轨迹服务


**接口地址**:`/api/trace/server`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>百度地图不支持通过接口删除，此方法仅仅是从数据库中删除数据，并不会从百度地图中删除，高德地图支持通过接口删除。<br/>百度地图管理地址：<a href="https://lbsyun.baidu.com/trace/admin/service">https://lbsyun.baidu.com/trace/admin/service</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "name": "",
  "provider": "NONE",
  "serverId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceServerParam|traceServerParam|body|true|TraceServerParam|TraceServerParam|
|&emsp;&emsp;desc|服务描述||false|string||
|&emsp;&emsp;name|服务名称||true|string||
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|服务id，创建时无需传入该参数。||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 查询单个轨迹服务


**接口地址**:`/api/trace/server/{serverId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>通过serverId查询单个轨迹服务信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|serverId|serverId|path|true|integer(int64)||
|provider|provider,可用值:BAIDU,AMAP,QQ,NONE|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«TraceServer»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|TraceServer|TraceServer|
|&emsp;&emsp;created||string||
|&emsp;&emsp;desc||string||
|&emsp;&emsp;id||integer||
|&emsp;&emsp;name||string||
|&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;serverId||integer||
|&emsp;&emsp;status||boolean||
|&emsp;&emsp;updated||string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"created": "",
		"desc": "",
		"id": 0,
		"name": "",
		"provider": "",
		"serverId": 0,
		"status": true,
		"updated": ""
	},
	"msg": ""
}
```


# 轨迹终端管理


## 查询终端列表


**接口地址**:`/api/trace/terminal`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>查询终端列表，如果指定了 终端id 或 名称 将查询具体的数据，否则查询列表数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|true|string||
|name|终端名称，非必须，如果指定就按照终端名称查询，否则查询列表|query|false|string||
|page|页数，默认：1|query|false|string||
|pageSize|页面大小，默认：50|query|false|string||
|serverId|服务id|query|false|string||
|terminalId|终端id，非必须，如果指定就按照终端id查询，否则查询列表|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«PageResult«TraceTerminal»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|PageResult«TraceTerminal»|PageResult«TraceTerminal»|
|&emsp;&emsp;items|数据列表|array|TraceTerminal|
|&emsp;&emsp;&emsp;&emsp;created||string||
|&emsp;&emsp;&emsp;&emsp;desc||string||
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;props||string||
|&emsp;&emsp;&emsp;&emsp;provider|可用值:BAIDU,AMAP,QQ,NONE|string||
|&emsp;&emsp;&emsp;&emsp;serverId||integer||
|&emsp;&emsp;&emsp;&emsp;terminalId||integer||
|&emsp;&emsp;&emsp;&emsp;updated||string||
|&emsp;&emsp;page|当前页码|integer||
|&emsp;&emsp;pageCount|总页数|integer||
|&emsp;&emsp;pageSize|页面大小|integer||
|&emsp;&emsp;total|总记录数|integer||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"items": [
			{
				"created": "",
				"desc": "",
				"id": 0,
				"name": "",
				"props": "",
				"provider": "",
				"serverId": 0,
				"terminalId": 0,
				"updated": ""
			}
		],
		"page": 0,
		"pageCount": 0,
		"pageSize": 0,
		"total": 0
	},
	"msg": ""
}
```


## 创建终端


**接口地址**:`/api/trace/terminal`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/terminal">https://lbs.amap.com/api/track/lieying-kaifa/api/terminal</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "name": "",
  "props": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceTerminalParam|traceTerminalParam|body|true|TraceTerminalParam|TraceTerminalParam|
|&emsp;&emsp;desc|终端描述||false|string||
|&emsp;&emsp;name|终端名称||true|string||
|&emsp;&emsp;props|用户自定义字段，具体参考百度或高德的文档||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id ||true|integer||
|&emsp;&emsp;terminalId|终端id，创建时无需指定||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 更新终端


**接口地址**:`/api/trace/terminal`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/terminal">https://lbs.amap.com/api/track/lieying-kaifa/api/terminal</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "name": "",
  "props": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceTerminalParam|traceTerminalParam|body|true|TraceTerminalParam|TraceTerminalParam|
|&emsp;&emsp;desc|终端描述||false|string||
|&emsp;&emsp;name|终端名称||true|string||
|&emsp;&emsp;props|用户自定义字段，具体参考百度或高德的文档||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id ||true|integer||
|&emsp;&emsp;terminalId|终端id，创建时无需指定||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 删除终端


**接口地址**:`/api/trace/terminal`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>百度地图：<a href="https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity">https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity</a><br/>高德地图：<a href="https://lbs.amap.com/api/track/lieying-kaifa/api/terminal">https://lbs.amap.com/api/track/lieying-kaifa/api/terminal</a></p>



**请求示例**:


```javascript
{
  "desc": "",
  "name": "",
  "props": {},
  "provider": "NONE",
  "serverId": 0,
  "terminalId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|traceTerminalParam|traceTerminalParam|body|true|TraceTerminalParam|TraceTerminalParam|
|&emsp;&emsp;desc|终端描述||false|string||
|&emsp;&emsp;name|终端名称||true|string||
|&emsp;&emsp;props|用户自定义字段，具体参考百度或高德的文档||false|object||
|&emsp;&emsp;provider|服务提供商，必须大写，默认：高德地图 ,可用值:BAIDU,AMAP,QQ,NONE||false|string||
|&emsp;&emsp;serverId|地图服务商中的服务id ||true|integer||
|&emsp;&emsp;terminalId|终端id，创建时无需指定||true|integer||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 查询终端在某个轨迹中的最新位置


**接口地址**:`/api/trace/terminal/last/point`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>查询终端在某个轨迹中的最新位置</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|provider|服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图,可用值:BAIDU,AMAP,QQ,NONE|query|true|string||
|serverId|服务id|query|false|string||
|terminalId|终端id|query|false|string||
|traceId|轨迹id|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|R«string»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|状态编码：0-成功，1-失败|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|提示消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```