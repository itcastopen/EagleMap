package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import cn.itcast.em.vo.CoordinateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class DirectionParam {
    @ApiModelProperty(value = "服务提供商，必须大写，默认为：高德地图", example = "NONE")
    private ServerType provider = ServerType.NONE;
    @ApiModelProperty(value = "起点坐标", required = true)
    private CoordinateVo origin;
    @ApiModelProperty(value = "终点坐标", required = true)
    private CoordinateVo destination;
    @ApiModelProperty(value = "请求参数，不同服务商参数不同： <br/>百度地图参考：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-0 <br/> <br/>高德地图参考：https://lbs.amap.com/api/webservice/guide/api/direction#driving", required = true)
    private Map<String, Object> param;
}
