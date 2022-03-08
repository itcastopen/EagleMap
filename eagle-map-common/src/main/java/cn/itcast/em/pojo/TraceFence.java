package cn.itcast.em.pojo;

import cn.itcast.em.enums.FenceType;
import cn.itcast.em.enums.ServerType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 电子围栏
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TraceFence extends BasePojo {

    private ServerType provider; //地图服务商
    private Long serverId; //地图服务商中的服务id
    private Long fenceId; //地图服务商的围栏id
    private String name; //围栏名称
    @TableField("`desc`")
    private String desc; //围栏描述
    private FenceType type; //电子围栏类型，1-圆形，2-多边形，3-线性，4-行政区
    private String param; //创建围栏的参数

}
