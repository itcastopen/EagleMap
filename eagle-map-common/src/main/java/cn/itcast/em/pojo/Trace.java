package cn.itcast.em.pojo;

import cn.itcast.em.enums.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 轨迹
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trace extends BasePojo {

    private ProviderType provider; //地图服务商
    private Long serverId; //所属的服务id
    private Long terminalId; //所属的终端id
    private Long traceId; //地图服务商的轨迹id
    private String name; //轨迹名称
    private Integer status; //状态，0-运动中，1-已结束
    private Date startTime; //开始时间
    private Date endTime; //结束时间
    private Integer size; //轨迹点数量
    private Double distance; //此段轨迹的里程数，单位：米
    private Long time; //轨迹持续时间，单位：毫秒
    private String startPoint; //起点坐标，经纬度，逗号分隔
    private String endPoint; //终点坐标，经纬度，逗号分隔
    private String pointList; //轨迹点数据，json格式

}
