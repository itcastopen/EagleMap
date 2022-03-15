package cn.itcast.em.pojo;

import cn.itcast.em.enums.ServerType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 轨迹所属服务
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TraceServer extends BasePojo {

    private ServerType provider; //地图服务商
    private Long serverId; //地图服务商中的服务id
    private String name; //服务名称
    @TableField("`desc`")
    private String desc; //服务描述
    private Boolean status; //服务描述

}
