package cn.itcast.em.server.service.impl.baidu;

import cn.hutool.http.HttpRequest;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.service.impl.BaseMapApiService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduWebApiService extends BaseMapApiService {

    @Resource
    private BaiduServerConfig baiduServerConfig;

    @Override
    protected void setRequestParam(HttpRequest httpRequest, Map<String, Object> param) {
        httpRequest.form(param)
                .form("ak", this.baiduServerConfig.getServer().getAk());
    }
}
