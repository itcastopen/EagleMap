package com.itheima.em.admin.vo;

import com.itheima.em.enums.ProviderType;
import lombok.Data;

import java.util.Date;

/**
 * @author zzj
 * @version 1.0
 * @date 2022/3/22
 */
@Data
public class TraceTerminalVO {

    private ProviderType provider; //地图服务商
    private Long serverId; //服务id
    private Long terminalId; //终端
    private String name; //终端名称
    private String desc; //终端描述
    private String props; //用户自定义字段,json格式
    private String location; //用户当前位置：经度,纬度
    private Boolean isOut; //用户当前位置：经度,纬度
    private Date created; //创建时间
    private Date updated; //更新时间

}
