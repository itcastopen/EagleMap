package cn.itcast.em.service;

import cn.itcast.em.enums.FenceType;
import cn.itcast.em.pojo.TraceFence;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;

import java.util.Map;

/**
 * 电子围栏相关业务
 */
public interface FenceService extends EagleMapService {

    /**
     * 创建电子围栏
     *
     * @param serverId  服务id
     * @param name      围栏名称
     * @param desc      描述
     * @param fenceType 围栏类型
     * @param param     参数
     * @return 创建成功，返回围栏id
     */
    R<Long> createFence(Long serverId, String name, String desc, FenceType fenceType, Map<String, Object> param);


    /**
     * 更新电子围栏
     *
     * @param serverId  服务id
     * @param fenceId   围栏id
     * @param name      围栏名称
     * @param desc      描述
     * @param fenceType 围栏类型
     * @param param     参数
     * @return 创建成功，返回围栏id
     */
    R<String> updateFence(Long serverId, Long fenceId, String name, String desc, FenceType fenceType, Map<String, Object> param);

    /**
     * 删除电子围栏
     *
     * @param serverId 服务id
     * @param fenceIds 围栏id
     * @return
     */
    R<String> deleteFence(Long serverId, Long... fenceIds);


    /**
     * 绑定终端到电子围栏中
     *
     * @param serverId    服务id
     * @param fenceId     围栏id
     * @param terminalIds 终端id列表
     * @return
     */
    R<String> bindTerminalFence(Long serverId, Long fenceId, Long... terminalIds);

    /**
     * 解绑电子围栏中的终端
     *
     * @param serverId    服务id
     * @param fenceId     围栏id
     * @param terminalIds 终端id列表
     * @return
     */
    R<String> unbindTerminalFence(Long serverId, Long fenceId, Long... terminalIds);

    /**
     * 分页查询围栏中的终端列表
     *
     * @param serverId 服务id
     * @param fenceId  围栏id
     * @param page     页数
     * @param pageSize 页面大小
     * @return
     */
    R<PageResult<TraceTerminal>> queryTerminalFenceList(Long serverId, Long fenceId, Integer page, Integer pageSize);

    /**
     * 分页查询围栏列表
     *
     * @param page     页数
     * @param pageSize 页面大小
     * @return
     */
    R<PageResult<TraceFence>> queryFenceList(Integer page, Integer pageSize);

    /**
     * 根据围栏id查询围栏信息
     *
     * @param serverId 服务id
     * @param fenceId  围栏id
     * @return 围栏对象数据
     */
    R<TraceFence> queryByFenceId(Long serverId, Long fenceId);
}
