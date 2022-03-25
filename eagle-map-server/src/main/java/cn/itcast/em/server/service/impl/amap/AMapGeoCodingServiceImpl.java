package cn.itcast.em.server.service.impl.amap;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.AMapServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.exception.EagleMapException;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.service.GeoCodeService;
import cn.itcast.em.vo.CoordinateVo;
import cn.itcast.em.vo.GeoResultVO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 地理/逆地理编码 - 高德地图的实现类
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/25
 */
@Service("AMapGeoCodingService")
@ConditionalOnBean(AMapServerConfig.class)
public class AMapGeoCodingServiceImpl implements GeoCodeService {

    @Resource
    private EagleConfig eagleConfig;
    @Resource
    private AMapWebApiService aMapWebApiService;

    @Override
    public GeoResultVO geoCode(String address, Map<String, Object> param) {
        String url = this.eagleConfig.getAmapWebApi() + "/v3/geocode/geo";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }

        requestParam.put("address", address);
        requestParam.put("output", "JSON");

        return this.aMapWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 1) {
                throw new EagleMapException(body);
            }

            JSONArray geocodes = json.getJSONArray("geocodes");
            if (CollUtil.isEmpty(geocodes)) {
                throw new EagleMapException("No matching data.");
            }

            JSONObject geo = (JSONObject) geocodes.get(0);
            GeoResultVO geoResultVO = new GeoResultVO();
            geoResultVO.setAddress(geo.getStr("formatted_address"));
            geoResultVO.setCountry(geo.getStr("country"));
            geoResultVO.setProvince(geo.getStr("province"));
            geoResultVO.setCity(geo.getStr("city"));
            geoResultVO.setDistrict(geo.getStr("district"));
            geoResultVO.setLocation(new CoordinateVo(geo.getStr("location")));
            geoResultVO.setData(geocodes.toString());

            return geoResultVO;
        });
    }

    @Override
    public GeoResultVO geoDecode(Double longitude, Double latitude, Map<String, Object> param) {
        String url = this.eagleConfig.getAmapWebApi() + "/v3/geocode/regeo";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }
        requestParam.put("location", longitude + "," + latitude);

        return this.aMapWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 1) {
                throw new EagleMapException(body);
            }

            JSONObject reGeoCode = json.getJSONObject("regeocode");

            GeoResultVO geoResultVO = new GeoResultVO();
            geoResultVO.setAddress(reGeoCode.getStr("formatted_address"));
            JSONObject addressComponent = reGeoCode.getJSONObject("addressComponent");
            geoResultVO.setCountry(addressComponent.getStr("country"));
            geoResultVO.setProvince(addressComponent.getStr("province"));
            geoResultVO.setCity(addressComponent.getStr("city"));
            geoResultVO.setDistrict(addressComponent.getStr("district"));
            geoResultVO.setLocation(new CoordinateVo(longitude, latitude));
            geoResultVO.setData(reGeoCode.toString());

            return geoResultVO;
        });
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.AMAP;
    }

    @Override
    public int getOrder() {
        return EagleOrdered.ONE;
    }
}
