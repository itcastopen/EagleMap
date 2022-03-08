package cn.itcast.em.service;

import cn.itcast.em.vo.IpResultVo;

/**
 * IP定位服务
 */
public interface IPService extends EagleMapService {

    /**
     * ip定位
     *
     * @param ip   ip值，如：140.206.149.83
     * @param type IP类型,值为 4 或 6，4 表示 IPv4，6 表示 IPv6
     * @return
     */
    IpResultVo query(String ip, int type);

}
