package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FenceParam {

    private ServerType provider = ServerType.NONE;
    private Long serverId; //地图服务商中的服务id
    private String name; //名称
    private String desc; //描述
    private Long terminalId; //终端id
    private Long fenceId; //围栏id
    private Long[] fenceIds; //围栏id列表
    private Long[] terminalIds; //终端id列表
    private Map<String, Object> param; //其他请求参数
    private Integer page = 1; //页数
    private Integer pageSize = 20; //页面大小

}
