package cn.itcast.em.server.service.impl.amap;

import cn.hutool.http.HttpRequest;
import cn.itcast.em.config.AMapServerConfig;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.service.impl.BaseMapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@ConditionalOnBean(AMapServerConfig.class)
public class AMapWebApiService extends BaseMapApiService {

    @Resource
    private AMapServerConfig aMapServerConfig;

    @Override
    public void setRequestParam(HttpRequest httpRequest, Map<String, Object> param) {
        httpRequest.form(param)
                .form("key", this.aMapServerConfig.getServer().getAk());
    }

}
