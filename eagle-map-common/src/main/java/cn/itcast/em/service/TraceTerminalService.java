package cn.itcast.em.service;

import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.EagleMapService;
import cn.itcast.em.vo.CoordinateVo;
import cn.itcast.em.vo.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 轨迹终端相关业务
 */
public interface TraceTerminalService extends EagleMapService {

    /**
     * 创建终端
     *
     * @param serverId 服务id
     * @param name     名称
     * @param desc     描述
     * @param props    自定义字段
     * @return
     */
    String create(Long serverId, String name, String desc, Map<String, Object> props);

    /**
     * 不指定服务，由程序自动选择服务，如果没有可用的服务会自动创建服务
     *
     * @param name  名称
     * @param desc  描述
     * @param props 自定义字段
     * @return
     */
    String create(String name, String desc, Map<String, Object> props);

    /**
     * 删除终端
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @return 成功返回null，否则返回错误详情
     */
    String delete(Long serverId, Long terminalId);

    /**
     * 更新终端
     *
     * @param traceTerminal 终端对象数据
     * @return 成功返回null，否则返回错误详情
     */
    String update(TraceTerminal traceTerminal);

    /**
     * 查询终端列表，如果指定了 终端id 或 名称 将查询具体的数据，否则查询列表数据
     *
     * @param serverId   服务id（必填）
     * @param terminalId 终端id （非必须）
     * @param name       名称（非必须）
     * @param page       页数
     * @param pageSize   页面大小
     * @return
     */
    PageResult<TraceTerminal> queryList(Long serverId, Long terminalId, String name, Integer page, Integer pageSize);

    /**
     * 查询终端在某个轨迹中的最新位置
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @return 服务商响应的数据
     */
    String queryLastPoint(Long serverId, Long terminalId, Long traceId);

}
