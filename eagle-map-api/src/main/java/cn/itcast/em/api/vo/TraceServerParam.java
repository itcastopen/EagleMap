package cn.itcast.em.api.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.pojo.TraceServer;
import cn.itcast.em.vo.CoordinateVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class TraceServerParam {

    @ApiModelProperty(value = "服务提供商，必须大写，默认：高德地图 ", example = "NONE")
    private ServerType provider = ServerType.NONE;
    @ApiModelProperty(value = "服务名称", required = true)
    private String name;
    @ApiModelProperty(value = "服务描述")
    private String desc;
    @ApiModelProperty(value = "服务id，创建时无需传入该参数。", required = true)
    private Long serverId;

}
