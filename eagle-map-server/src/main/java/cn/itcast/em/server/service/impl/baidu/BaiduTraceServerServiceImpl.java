package cn.itcast.em.server.service.impl.baidu;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.mapper.TraceServerMapper;
import cn.itcast.em.pojo.TraceServer;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.service.TraceServerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 百度地图轨迹服务
 */
@Service("BaiduTraceServerService")
@ConditionalOnBean(BaiduServerConfig.class)
@ConditionalOnProperty(name = "eagle.service-mode", havingValue = "COMPLETE")
public class BaiduTraceServerServiceImpl extends ServiceImpl<TraceServerMapper, TraceServer> implements TraceServerService {

    @Resource
    private BaiduServerConfig baiduServerConfig;

    @Override
    public String create(String name, String desc) {
        //百度地图不支持通过api方式对鹰眼轨迹服务进行操作. 请通过百度地图的web管理端进行操作。
        return "err: Baidu map does not support the operation of eagle eye track service through API Please operate through the web management end of Baidu map.";
    }

    @Override
    public String delete(Long serverId) {
        LambdaQueryWrapper<TraceServer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TraceServer::getProvider, ProviderType.BAIDU);
        lambdaQueryWrapper.eq(TraceServer::getServerId, serverId);

        boolean result = super.remove(lambdaQueryWrapper);
        if (result) {
            return "The deletion was successful. It was only deleted from the database, but not from Baidu map.";
        }

        return "err: Delete from database failed.";
    }

    @Override
    public String update(Long serverId, String name, String desc) {
        //更新数据库中的数据
        TraceServer traceServer = new TraceServer();
        traceServer.setName(name);
        traceServer.setDesc(desc);

        LambdaQueryWrapper<TraceServer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceServer::getServerId, serverId);
        queryWrapper.eq(TraceServer::getProvider, ProviderType.BAIDU);

        boolean result = super.update(traceServer, queryWrapper);
        if (result) {
            return "The update was successful. It was only update from the database, but not from Baidu map.";
        }

        return "err: Update from database failed.";
    }

    @Override
    public List<TraceServer> queryAll() {
        LambdaQueryWrapper<TraceServer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TraceServer::getProvider, ProviderType.BAIDU);
        return super.list(lambdaQueryWrapper);
    }

    @Override
    public TraceServer queryByServerId(Long serverId) {
        LambdaQueryWrapper<TraceServer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TraceServer::getProvider, ProviderType.BAIDU);
        lambdaQueryWrapper.eq(TraceServer::getServerId, serverId);
        return super.getOne(lambdaQueryWrapper);
    }


    @Override
    public TraceServer queryAvailableServer() {
        LambdaQueryWrapper<TraceServer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceServer::getStatus, true);
        queryWrapper.eq(TraceServer::getProvider, ProviderType.BAIDU);

        List<TraceServer> serverList = super.list(queryWrapper);
        if (CollUtil.isNotEmpty(serverList)) {
            return serverList.get(0);
        }
        return null;
    }

    @Override
    public Boolean markNotAvailable(Long serverId) {
        LambdaQueryWrapper<TraceServer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceServer::getServerId, serverId);
        queryWrapper.eq(TraceServer::getProvider, ProviderType.BAIDU);

        TraceServer traceServer = new TraceServer();
        traceServer.setStatus(false);
        return super.update(traceServer, queryWrapper);
    }

    @Override
    public int getOrder() {
        return EagleOrdered.LOWEST_PRECEDENCE;
    }

    /**
     * 初始化时，将配置文件中的轨迹服务保存到数据库
     * 规则：配置文件中有，数据库中没有，新增操作，如果配置文件中没有，而数据库中的话，请通过禁用、或删除接口将其删除
     */
    @PostConstruct
    private void init() {
        List<TraceServer> traceServerList = this.baiduServerConfig.getTraceServers().stream()
                .map(traceServerConfig -> {
                    TraceServer traceServer = new TraceServer();
                    traceServer.setServerId(traceServerConfig.getId());
                    traceServer.setName(traceServerConfig.getName());
                    traceServer.setDesc(traceServerConfig.getType());
                    traceServer.setProvider(ProviderType.BAIDU);
                    traceServer.setStatus(true);
                    traceServer.setCreated(DateUtil.parse(traceServerConfig.getDate(), "yyyy-MM-dd"));
                    return traceServer;
                })
                .collect(Collectors.toList());

        for (TraceServer traceServer : traceServerList) {
            LambdaQueryWrapper<TraceServer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TraceServer::getProvider, ProviderType.BAIDU);
            lambdaQueryWrapper.eq(TraceServer::getServerId, traceServer.getServerId());

            if (super.count(lambdaQueryWrapper) == 0) {
                //不存在
                super.save(traceServer);
            }
        }
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.BAIDU;
    }
}
