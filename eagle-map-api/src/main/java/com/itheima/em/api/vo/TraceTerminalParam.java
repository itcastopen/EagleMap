package com.itheima.em.api.vo;

import com.itheima.em.enums.ProviderType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class TraceTerminalParam {

    @ApiModelProperty(value = "服务提供商，必须大写，默认：高德地图 ", example = "NONE")
    private ProviderType provider = ProviderType.NONE;
    @ApiModelProperty(value = "地图服务商中的服务id ", required = true)
    private Long serverId; //地图服务商中的服务id
    @ApiModelProperty(value = "终端id，创建时无需指定", required = true)
    private Long terminalId; //终端id
    @ApiModelProperty(value = "终端名称", required = true)
    private String name; //名称
    @ApiModelProperty(value = "终端描述")
    private String desc; //终端描述
    @ApiModelProperty(value = "用户自定义字段，具体参考百度或高德的文档")
    private Map<String, Object> props; //用户自定义字段

}
