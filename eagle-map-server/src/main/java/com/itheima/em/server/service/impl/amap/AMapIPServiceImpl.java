package com.itheima.em.server.service.impl.amap;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.itheima.em.config.AMapServerConfig;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.service.EagleOrdered;
import com.itheima.em.service.IPService;
import com.itheima.em.vo.IpResultVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("AMapIPService")
@ConditionalOnBean(AMapServerConfig.class)
public class AMapIPServiceImpl implements IPService {

    @Resource
    private AMapWebApiService aMapWebApiService;
    @Resource
    private EagleConfig eagleConfig;

    @Override
    public int getOrder() {
        return EagleOrdered.ONE;
    }

    @Override
    public IpResultVo query(String ip, int type) {
        String url = this.eagleConfig.getAmapWebApi() + "/v5/ip";
        //封装请求参数
        Map<String, Object> param = new HashMap<>();
        param.put("type", type);
        param.put("ip", ip);
        return this.aMapWebApiService.doGet(url, param, response -> {
            if (!response.isOk()) {
                return null;
            }
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 1) {
                return null;
            }
            return JSONUtil.toBean(body, IpResultVo.class);
        });
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.AMAP;
    }
}
