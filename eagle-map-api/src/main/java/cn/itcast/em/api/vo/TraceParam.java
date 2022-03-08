package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TraceParam {

    private ServerType provider = ServerType.NONE;
    private Long serverId; //地图服务商中的服务id
    private Long terminalId; //终端id
    private Long traceId; //轨迹id
    private String name; //名称
    private List<Map<String, Object>> pointList; //轨迹点
    private Map<String, Object> param; //其他请求参数

}
