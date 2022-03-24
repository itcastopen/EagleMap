package cn.itcast.em.service;

import cn.itcast.em.pojo.TraceServer;

import java.util.List;

/**
 * 轨迹服务相关业务操作
 */
public interface TraceServerService extends EagleMapService {


    /**
     * 创建服务
     *
     * @param name 服务名称
     * @param desc 服务描述，非必须
     * @return 地图服务商的服务id
     */
    String create(String name, String desc);

    /**
     * 根据服务id删除服务
     *
     * @param serverId 地图服务商服务id
     * @return 成功返回null，否则返回错误详情
     */
    String delete(Long serverId);


    /**
     * 更新数据
     *
     * @param serverId 地图服务商服务id
     * @param name     名称
     * @param desc     描述
     * @return 成功返回null，否则返回错误详情
     */
    String update(Long serverId, String name, String desc);

    /**
     * 查询所有服务
     *
     * @return
     */
    List<TraceServer> queryAll();


    /**
     * 根据主键查询
     *
     * @param serverId 地图服务商服务id
     * @return
     */
    TraceServer queryByServerId(Long serverId);

    /**
     * 获取一个可用的服务
     *
     * @return
     */
    TraceServer queryAvailableServer();

    /**
     * 标记服务不可用
     *
     * @param serverId
     * @return
     */
    Boolean markNotAvailable(Long serverId);
}
