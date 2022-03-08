package cn.itcast.em.api.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.pojo.TraceServer;
import cn.itcast.em.vo.CoordinateVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class TraceServerParam {

    private ServerType provider = ServerType.NONE;
    private String name;
    private String desc;
    private Long serverId;

}
