package com.itheima.em.api.vo;

import com.itheima.em.enums.ProviderType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class FenceParam {

    @ApiModelProperty(value = "服务提供商，必须大写，默认为：高德地图", example = "NONE")
    private ProviderType provider = ProviderType.NONE;
    @ApiModelProperty(value = "地图服务商中的服务id", required = true)
    private Long serverId; //地图服务商中的服务id
    @ApiModelProperty(value = "围栏名称", required = true)
    private String name; //名称
    @ApiModelProperty(value = "围栏描述", required = true)
    private String desc; //描述
    @ApiModelProperty(value = "终端id")
    private Long terminalId; //终端id
    @ApiModelProperty(value = "围栏id")
    private Long fenceId; //围栏id
    @ApiModelProperty(value = "围栏id列表")
    private Long[] fenceIds; //围栏id列表
    @ApiModelProperty(value = "终端id列表")
    private Long[] terminalIds; //终端id列表
    @ApiModelProperty(value = "其他请求参数")
    private Map<String, Object> param; //其他请求参数
    @ApiModelProperty(value = "页数，默认：1")
    private Integer page = 1; //页数
    @ApiModelProperty(value = "页面大小，默认：20")
    private Integer pageSize = 20; //页面大小

}
