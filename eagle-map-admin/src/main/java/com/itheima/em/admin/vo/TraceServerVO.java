package com.itheima.em.admin.vo;

import cn.hutool.core.bean.BeanUtil;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.pojo.TraceServer;
import lombok.Data;

import java.util.Date;

/**
 * @author zzj
 * @version 1.0
 * @date 2022/3/18
 */
@Data
public class TraceServerVO {

    private Long id; //地图服务商中的服务id
    private ProviderType provider; //地图服务商
    private String name; //服务名称
    private String desc; //服务描述
    private Boolean status; //服务描述
    private Date created; //创建时间
    private Date updated; //更新时间

    public static TraceServerVO convert(TraceServer traceServer) {
        TraceServerVO traceServerVO = BeanUtil.toBeanIgnoreError(traceServer, TraceServerVO.class);
        traceServerVO.setId(traceServer.getServerId());
        return traceServerVO;
    }

}
