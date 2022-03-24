package cn.itcast.em.server.service.impl.amap;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.AMapServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.service.IPService;
import cn.itcast.em.vo.IpResultVo;
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
