package com.itheima.em.server.service.impl.baidu;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.itheima.em.config.BaiduServerConfig;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.CoordinateType;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.service.CoordinateService;
import com.itheima.em.service.EagleOrdered;
import com.itheima.em.vo.CoordinateVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 百度地图的实现类
 */
@Service("BaiduCoordinateService")
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduCoordinateServiceImpl implements CoordinateService {

    @Resource
    private BaiduWebApiService baiduWebApiService;
    @Resource
    private EagleConfig eagleConfig;

    @Override
    public List<CoordinateVo> convertToGcj02(CoordinateType fromType, CoordinateVo... froms) {
        String url = this.eagleConfig.getBaiduWebApi() + "/geoconv/v1/";

        //封装请求参数
        Map<String, Object> param = new HashMap<>();
        param.put("coords", CollUtil.join(Arrays.stream(froms).map(vo -> vo.getLongitude() + "," + vo.getLatitude())
                .collect(Collectors.toList()), ";"));
        param.put("from", fromType.getBaiduValue());
        param.put("to", CoordinateType.EAGLE.getBaiduValue());
        param.put("output", "json");

        return this.baiduWebApiService.doGet(url, param, response -> {
            if (!response.isOk()) {
                return Collections.emptyList();
            }
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 0) {
                return Collections.emptyList();
            }

            return json.getJSONArray("result").stream()
                    .map(o -> {
                        JSONObject jsonObject = (JSONObject) o;
                        return new CoordinateVo(jsonObject.getDouble("x"), jsonObject.getDouble("y"));
                    }).collect(Collectors.toList());
        });
    }

    @Override
    public CoordinateVo convert(CoordinateVo from, CoordinateType fromType, CoordinateType toType) {
        if (toType == CoordinateType.GPS || toType == CoordinateType.SOU_GOU) {
            throw new RuntimeException("Unsupported coordinate type!");
        }
        String url = this.eagleConfig.getBaiduWebApi() + "/geoconv/v1/";

        //封装请求参数
        Map<String, Object> param = new HashMap<>();
        param.put("coords", from.getLongitude() + "," + from.getLatitude());
        param.put("from", fromType.getBaiduValue());
        param.put("to", toType.getBaiduValue());
        param.put("output", "json");

        return this.baiduWebApiService.doGet(url, param, response -> {
            if (!response.isOk()) {
                return null;
            }
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 0) {
                return null;
            }

            JSONArray result = json.getJSONArray("result");
            JSONObject jsonObject = (JSONObject) result.get(0);
            return new CoordinateVo(jsonObject.getDouble("x"), jsonObject.getDouble("y"));
        });
    }

    @Override
    public int getOrder() {
        return EagleOrdered.ONE;
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.BAIDU;
    }
}
