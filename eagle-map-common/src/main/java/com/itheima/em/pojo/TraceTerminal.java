package com.itheima.em.pojo;

import com.itheima.em.enums.ProviderType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 轨迹的终端表
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TraceTerminal extends BasePojo {

    private ProviderType provider; //地图服务商
    private Long serverId; //地图服务商中的服务id
    private Long terminalId; //终端id
    private String name; //终端名称
    @TableField("`desc`")
    private String desc; //终端描述
    private String props; //用户自定义字段

}
