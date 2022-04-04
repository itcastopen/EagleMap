package com.itheima.em.server.service.impl.amap;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import com.itheima.em.config.AMapServerConfig;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.service.EagleOrdered;
import com.itheima.em.service.StaticMapService;
import com.itheima.em.vo.CoordinateVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 高德地图的实现类
 */
@Service("AMapStaticMapService")
@ConditionalOnBean(AMapServerConfig.class)
public class AMapStaticMapServiceImpl implements StaticMapService {

    @Resource
    private AMapWebApiService aMapWebApiService;
    @Resource
    private EagleConfig eagleConfig;

    @Override
    public int getOrder() {
        return EagleOrdered.ONE;
    }

    @Override
    public String query(CoordinateVo location, Integer width, Integer height, Integer zoom, Map<String, Object> param) {
        String url = this.eagleConfig.getAmapWebApi() + "/v3/staticmap";

        //封装轻骑参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("location", location.toParam());
        requestParam.put("zoom", zoom);
        requestParam.put("size", width + "*" + height);
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }

        return this.aMapWebApiService.doGet(url, requestParam, response -> {
            if (!response.isOk()) {
                return null;
            }
            return Base64.encode(response.bodyStream());
        });
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.AMAP;
    }
}
