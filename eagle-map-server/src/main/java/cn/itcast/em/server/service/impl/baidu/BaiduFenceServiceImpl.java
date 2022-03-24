package cn.itcast.em.server.service.impl.baidu;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.CoordinateType;
import cn.itcast.em.enums.FenceType;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.exception.EagleMapException;
import cn.itcast.em.mapper.TraceFenceMapper;
import cn.itcast.em.mapper.TraceTerminalMapper;
import cn.itcast.em.pojo.TraceFence;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.service.FenceService;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 百度地图电子围栏实现，文档：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/9
 */
@Service("BaiduFenceService")
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduFenceServiceImpl extends ServiceImpl<TraceFenceMapper, TraceFence> implements FenceService {

    @Resource
    private EagleConfig eagleConfig;
    @Resource
    private BaiduWebApiService baiduWebApiService;
    @Resource
    private TraceTerminalMapper traceTerminalMapper;

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
    @Override
    public R<Long> createFence(Long serverId, String name, String desc, FenceType fenceType, Map<String, Object> param) {
        if (CollUtil.isEmpty(param)) {
            return R.error("Param cannot be empty.");
        }
        //封装参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("fence_name", name);
        requestParam.put("coord_type", CoordinateType.EAGLE.getValue());
        //由于不同类型的围栏参数是不一样的，所以通过Map传入，让后将其封装到请求map中
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            requestParam.put(entry.getKey(), entry.getValue());
        }

        String url = this.eagleConfig.getBaiduYingYanApi();
        switch (fenceType) {
            case CIRCLE: {
                url += "/api/v3/fence/createcirclefence";
                break;
            }
            case POLYGON: {
                url += "/api/v3/fence/createpolygonfence";
                break;
            }
            case POLYLINE: {
                url += "/api/v3/fence/createpolylinefence";
                break;
            }
            case DISTRICT: {
                url += "/api/v3/fence/createdistrictfence";
                break;
            }
            default: {
                return R.error("FenceType is not in the available range.");
            }
        }

        return this.baiduWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("status") != 0) {
                return R.error(body);
            }

            //获取到围栏id返回
            Long fenceId = json.getLong("fence_id");

            //存储围栏数据到数据库
            TraceFence traceFence = new TraceFence();
            traceFence.setFenceId(fenceId);
            traceFence.setServerId(serverId);
            traceFence.setName(name);
            traceFence.setDesc(desc);
            traceFence.setProvider(ProviderType.BAIDU);
            traceFence.setParam(JSONUtil.toJsonStr(param));
            traceFence.setType(fenceType);
            super.save(traceFence);

            return R.success(fenceId);
        });
    }

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
    @Override
    public R<String> updateFence(Long serverId, Long fenceId, String name, String desc, FenceType fenceType, Map<String, Object> param) {
        if (CollUtil.isEmpty(param)) {
            return R.error("Param cannot be empty.");
        }
        //封装参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("fence_id", fenceId); //围栏id
        requestParam.put("fence_name", name);
        requestParam.put("coord_type", CoordinateType.EAGLE.getValue());
        //由于不同类型的围栏参数是不一样的，所以通过Map传入，让后将其封装到请求map中
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            requestParam.put(entry.getKey(), entry.getValue());
        }

        String url = this.eagleConfig.getBaiduYingYanApi();
        switch (fenceType) {
            case CIRCLE: {
                url += "/api/v3/fence/updatecirclefence";
                break;
            }
            case POLYGON: {
                url += "/api/v3/fence/updatepolygonfence";
                break;
            }
            case POLYLINE: {
                url += "/api/v3/fence/updatepolylinefence";
                break;
            }
            case DISTRICT: {
                url += "/api/v3/fence/updatedistrictfence";
                break;
            }
            default: {
                return R.error("FenceType is not in the available range.");
            }
        }

        return this.baiduWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("status") != 0) {
                return R.error(body);
            }
            //根据围栏id更新数据
            TraceFence traceFence = new TraceFence();
            traceFence.setName(name);
            traceFence.setDesc(desc);
            traceFence.setParam(JSONUtil.toJsonStr(param));

            LambdaQueryWrapper<TraceFence> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TraceFence::getFenceId, fenceId);
            queryWrapper.eq(TraceFence::getProvider, ProviderType.BAIDU);
            queryWrapper.eq(TraceFence::getServerId, serverId);
            super.update(traceFence, queryWrapper);

            return R.success();
        });
    }

    /**
     * 删除电子围栏
     *
     * @param serverId 服务id
     * @param fenceIds 围栏id
     * @return
     */
    @Override
    public R<String> deleteFence(Long serverId, Long... fenceIds) {
        if (fenceIds.length > 100) {
            return R.error("The number of fences cannot be greater than 100.");
        }
        //封装参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("fence_ids", ArrayUtil.join(fenceIds, ",")); //围栏id

        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/fence/delete";

        return this.baiduWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("status") != 0) {
                return R.error(body);
            }

            //删除数据库中的围栏数据
            LambdaQueryWrapper<TraceFence> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(TraceFence::getFenceId, fenceIds);
            queryWrapper.eq(TraceFence::getProvider, ProviderType.BAIDU);
            queryWrapper.eq(TraceFence::getServerId, serverId);
            super.remove(queryWrapper);

            return R.success();
        });
    }

    /**
     * 绑定终端到电子围栏中
     *
     * @param serverId    服务id
     * @param fenceId     围栏id
     * @param terminalIds 终端id列表
     * @return
     */
    @Override
    public R<String> bindTerminalFence(Long serverId, Long fenceId, Long... terminalIds) {
        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/fence/addmonitoredperson";
        return this.executeBindOrUnBind(url, serverId, fenceId, terminalIds);
    }

    /**
     * 解绑电子围栏中的终端
     *
     * @param serverId    服务id
     * @param fenceId     围栏id
     * @param terminalIds 终端id列表
     * @return
     */
    @Override
    public R<String> unbindTerminalFence(Long serverId, Long fenceId, Long... terminalIds) {
        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/fence/deletemonitoredperson";
        return this.executeBindOrUnBind(url, serverId, fenceId, terminalIds);
    }

    /**
     * 绑定与解绑的方法通用实现，通过传入的url区别绑定与解绑
     *
     * @param url         接口地址
     * @param serverId    服务id
     * @param fenceId     围栏id
     * @param terminalIds 终端id列表
     * @return
     */
    private R<String> executeBindOrUnBind(String url, Long serverId, Long fenceId, Long... terminalIds) {
        if (ArrayUtil.isEmpty(terminalIds) || terminalIds.length > 100) {
            return R.error("The number of terminals should be between 0 and 100.");
        }

        //根据终端id查询终端数据
        LambdaQueryWrapper<TraceTerminal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TraceTerminal::getId, terminalIds);
        List<TraceTerminal> traceTerminalList = this.traceTerminalMapper.selectList(queryWrapper);

        //封装参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("fence_id", fenceId); //围栏id
        requestParam.put("monitored_person", CollUtil.join(traceTerminalList.stream()
                .map(traceTerminal -> traceTerminal.getName())
                .collect(Collectors.toList()), ",")); //终端名称以逗号分割

        return this.baiduWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("status") != 0) {
                return R.error(body);
            }
            return R.success();
        });
    }

    /**
     * 分页查询围栏中的终端列表
     *
     * @param serverId 服务id
     * @param fenceId  围栏id
     * @param page     页数
     * @param pageSize 页面大小
     * @return 分页数据对象
     */
    @Override
    public R<PageResult<TraceTerminal>> queryTerminalFenceList(Long serverId, Long fenceId, Integer page, Integer pageSize) {

        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/fence/listmonitoredperson";
        //封装参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("fence_id", fenceId); //围栏id
        requestParam.put("page_index", page); //查询页数
        requestParam.put("page_size", pageSize); //单页数据数量
        PageResult<TraceTerminal> pageResult = new PageResult<>();
        List<TraceTerminal> list;
        try {
            list = this.baiduWebApiService.doGet(url, requestParam, response -> {
                String body = response.body();
                JSONObject json = JSONUtil.parseObj(body);
                if (!response.isOk() || json.getInt("status") != 0) {
                    throw EagleMapException.builder().msg(body).build();
                }

                pageResult.setTotal(json.getInt("total"));
                JSONArray terminalNames = json.getJSONArray("monitored_person");

                if (CollUtil.isEmpty(terminalNames)) {
                    return Collections.emptyList();
                }

                //查询终端列表
                LambdaQueryWrapper<TraceTerminal> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(TraceTerminal::getProvider, ProviderType.BAIDU);
                queryWrapper.eq(TraceTerminal::getServerId, serverId);
                queryWrapper.in(TraceTerminal::getName, terminalNames);
                return this.traceTerminalMapper.selectList(queryWrapper);
            });
        } catch (EagleMapException e) {
            return R.error(e.getMsg());
        }

        pageResult.setPage(page);
        pageResult.setPageSize(pageSize);
        pageResult.setItems(list);
        return R.success(pageResult);
    }

    /**
     * 分页查询围栏列表
     *
     * @param page     页数
     * @param pageSize 页面大小
     * @return
     */
    @Override
    public R<PageResult<TraceFence>> queryFenceList(Integer page, Integer pageSize) {
        Page<TraceFence> traceFencePage = new Page<>(page, pageSize);
        LambdaQueryWrapper<TraceFence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceFence::getProvider, ProviderType.BAIDU);
        queryWrapper.orderByDesc(TraceFence::getCreated);
        super.page(traceFencePage, queryWrapper);
        return R.success(new PageResult<TraceFence>().convert(traceFencePage));
    }

    /**
     * 根据围栏id查询围栏信息
     *
     * @param serverId 服务id
     * @param fenceId  围栏id
     * @return 围栏对象数据
     */
    @Override
    public R<TraceFence> queryByFenceId(Long serverId, Long fenceId) {
        LambdaQueryWrapper<TraceFence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceFence::getProvider, ProviderType.BAIDU);
        queryWrapper.eq(TraceFence::getFenceId, fenceId);
        queryWrapper.eq(TraceFence::getServerId, serverId);
        TraceFence traceFence = super.getOne(queryWrapper);
        if (null != traceFence) {
            return R.success(traceFence);
        }
        return R.error("The data queried does not exist.");
    }

    /**
     * 查询终端在围栏中的状态
     *
     * @param serverId   服务id
     * @param fenceId    围栏id
     * @param terminalId 终端id
     * @return 是否在围栏中
     */
    @Override
    public R<Boolean> queryTerminalStatus(Long serverId, Long fenceId, Long terminalId) {
        //查询终端数据
        TraceTerminal traceTerminal = this.traceTerminalMapper.selectById(terminalId);

        //封装参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("fence_ids", fenceId); //围栏id
        requestParam.put("monitored_person", traceTerminal.getName());

        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/fence/querystatus";

        return this.baiduWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("status") != 0) {
                return R.error(body);
            }
            return R.success(StrUtil.contains(body, "\"in\""));
        });
    }

    @Override
    public R<List<TraceFence>> searchTraceFenceList(Long fenceId, String fenceName) {
        LambdaQueryWrapper<TraceFence> queryWrapper = new LambdaQueryWrapper<>();
        if (null != fenceId) {
            queryWrapper.eq(TraceFence::getFenceId, fenceId);
        }
        if (StrUtil.isNotEmpty(fenceName)) {
            queryWrapper.like(TraceFence::getName, fenceName);
        }
        queryWrapper.eq(TraceFence::getProvider, ProviderType.BAIDU);
        return R.success(super.list(queryWrapper));
    }

    @Override
    public int getOrder() {
        return EagleOrdered.TWO;
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.BAIDU;
    }
}
