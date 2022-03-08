package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import cn.itcast.em.vo.CoordinateVo;
import lombok.Data;

@Data
public class ConvertParam {

    private ServerType provider = ServerType.NONE;
    //原坐标值
    private CoordinateVo coordinate;
    //原坐标类型
    private String fromType;
    //目标坐标类型
    private String toType;

}
