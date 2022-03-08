package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import lombok.Data;

import java.util.Map;

@Data
public class TraceTerminalParam {

    private ServerType provider = ServerType.NONE;
    private Long serverId; //地图服务商中的服务id
    private Long terminalId; //终端id
    private String name; //名称
    private String desc; //终端描述
    private Map<String, Object> props; //用户自定义字段

}
