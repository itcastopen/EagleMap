package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TraceParam {

    @ApiModelProperty(value = "服务提供商，必须大写，默认：高德地图 ", example = "NONE")
    private ServerType provider = ServerType.NONE;
    @ApiModelProperty(value = "地图服务商中的服务id", required = true)
    private Long serverId; //地图服务商中的服务id
    @ApiModelProperty(value = "终端id", required = true)
    private Long terminalId; //终端id
    @ApiModelProperty(value = "轨迹id，创建时无需传递", required = true)
    private Long traceId; //轨迹id
    @ApiModelProperty(value = "轨迹名称", required = true)
    private String name; //名称
    @ApiModelProperty(value = "轨迹点列表，只有在上报轨迹 和 查询轨迹时才需要传递")
    private List<Map<String, Object>> pointList; //轨迹点
    @ApiModelProperty(value = "其它参数，具体参考百度地图或高德地图文档")
    private Map<String, Object> param; //其他请求参数

}
