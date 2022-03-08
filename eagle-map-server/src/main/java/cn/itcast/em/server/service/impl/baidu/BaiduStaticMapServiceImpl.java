package cn.itcast.em.server.service.impl.baidu;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.service.StaticMapService;
import cn.itcast.em.vo.CoordinateVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("BaiduStaticMapService")
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduStaticMapServiceImpl implements StaticMapService {

    @Resource
    private BaiduWebApiService baiduWebApiService;
    @Resource
    private EagleConfig eagleConfig;

    @Override
    public String query(CoordinateVo location, Integer width, Integer height, Integer zoom, Map<String, Object> param) {
        String url = this.eagleConfig.getBaiduWebApi() + "/staticimage/v2";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("width", width);
        requestParam.put("height", height);
        requestParam.put("center", location.toParam());
        requestParam.put("zoom", zoom);
        if (CollUtil.isNotEmpty(param)) {
            //将用户的可选参数加入到请求参数列表中
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }

        return this.baiduWebApiService.doGet(url, requestParam, response -> {
            if (!response.isOk()) {
                return null;
            }
            return Base64.encode(response.bodyStream());
        });
    }

    @Override
    public int getOrder() {
        return EagleOrdered.TWO;
    }
}
