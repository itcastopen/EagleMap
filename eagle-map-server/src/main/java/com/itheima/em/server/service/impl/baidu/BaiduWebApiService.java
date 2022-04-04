package com.itheima.em.server.service.impl.baidu;

import cn.hutool.http.HttpRequest;
import com.itheima.em.config.BaiduServerConfig;
import com.itheima.em.service.impl.BaseMapApiService;
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
