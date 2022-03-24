package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ProviderType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TraceServerParam {

    @ApiModelProperty(value = "服务提供商，必须大写，默认：高德地图 ", example = "NONE")
    private ProviderType provider = ProviderType.NONE;
    @ApiModelProperty(value = "服务名称", required = true)
    private String name;
    @ApiModelProperty(value = "服务描述")
    private String desc;
    @ApiModelProperty(value = "服务id，创建时无需传入该参数。", required = true)
    private Long serverId;

}
