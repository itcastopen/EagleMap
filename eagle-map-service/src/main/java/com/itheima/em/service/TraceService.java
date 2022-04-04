package com.itheima.em.service;

import com.itheima.em.pojo.Trace;
import com.itheima.em.vo.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 轨迹相关业务操作
 */
public interface TraceService extends EagleMapService {


    /**
     * 创建轨迹
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param name       轨迹名称
     * @return 轨迹id
     */
    String create(Long serverId, Long terminalId, String name);

    /**
     * 删除轨迹
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @return
     */
    String delete(Long serverId, Long terminalId, Long traceId);


    /**
     * 上传轨迹
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @param pointList  轨迹点列表
     * @return
     */
    String upload(Long serverId, Long terminalId, Long traceId, List<Map<String, Object>> pointList);

    /**
     * 停止运动，该方法会将轨迹中的轨迹点数据持久化本地数据库中
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @param param      其他的请求参数，用于查询轨迹详情的参数
     * @return
     */
    String stopTrace(Long serverId, Long terminalId, Long traceId, Map<String, Object> param);


    /**
     * 分页查询轨迹列表，按照轨迹创建时间倒序排序
     *
     * @param page     页数
     * @param pageSize 页面大小
     * @return
     */
    PageResult<Trace> queryTracePageList(Integer page, Integer pageSize);

    /**
     * 分页查询轨迹列表，按照轨迹创建时间倒序排序
     *
     * @param traceId   轨迹id
     * @param traceName 轨迹名称
     * @return
     */
    List<Trace> searchTraceList(Long traceId, String traceName);

    /**
     * 查询轨迹详情，优先查询本地数据库，如本地库中没有数据，再查询地图服务商
     * 可以通过param参数中的local参数进行控制，默认为true，如果不希望查询本地库，就将其设置为false
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @param param      其他的请求参数，用于查询轨迹详情的参数
     * @return
     */
    Trace queryTraceInfo(Long serverId, Long terminalId, Long traceId, Map<String, Object> param);

}
