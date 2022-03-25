package cn.itcast.em.server.service.impl.baidu;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.CoordinateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.CoordinateType;
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
 * 地理/逆地理编码 - 百度地图的实现类
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/25
 */
@Service("BaiduGeoCodeService")
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduGeoCodeServiceImpl implements GeoCodeService {

    @Resource
    private BaiduWebApiService baiduWebApiService;
    @Resource
    private EagleConfig eagleConfig;

    @Override
    public GeoResultVO geoCode(String address, Map<String, Object> param) {
        String url = this.eagleConfig.getBaiduWebApi() + "/geocoding/v3/";

        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }
        requestParam.put("address", address);
        requestParam.put("ret_coordtype", CoordinateType.BAIDU.getValue());
        requestParam.put("output", "json");

        return this.baiduWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 0) {
                throw new EagleMapException(body);
            }
            GeoResultVO geoResultVO = new GeoResultVO();
            geoResultVO.setAddress(address);
            JSONObject location = json.getByPath("result.location", JSONObject.class);
            CoordinateUtil.Coordinate coordinate = CoordinateUtil.bd09ToGcj02(location.getDouble("lng"), location.getDouble("lat"));
            geoResultVO.setLocation(new CoordinateVo(coordinate.getLng(), coordinate.getLat()));
            geoResultVO.setData(json.getJSONObject("result").toString());
            return geoResultVO;
        });
    }

    @Override
    public GeoResultVO geoDecode(Double longitude, Double latitude, Map<String, Object> param) {
        String url = this.eagleConfig.getBaiduWebApi() + "/reverse_geocoding/v3/";

        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }

        requestParam.put("location", latitude + "," + longitude);
        requestParam.put("coordtype", CoordinateType.EAGLE.getValue());
        requestParam.put("ret_coordtype", CoordinateType.EAGLE.getValue());
        requestParam.put("output", "json");

        return this.baiduWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 0) {
                throw new EagleMapException(body);
            }
            GeoResultVO geoResultVO = new GeoResultVO();
            JSONObject location = json.getByPath("result.location", JSONObject.class);
            geoResultVO.setLocation(new CoordinateVo(location.getDouble("lng"), location.getDouble("lat")));
            geoResultVO.setData(json.getJSONObject("result").toString());
            geoResultVO.setAddress(json.getByPath("result.formatted_address", String.class));

            JSONObject addressComponent = json.getByPath("result.addressComponent", JSONObject.class);
            geoResultVO.setCountry(addressComponent.getStr("country"));
            geoResultVO.setProvince(addressComponent.getStr("province"));
            geoResultVO.setCity(addressComponent.getStr("city"));
            geoResultVO.setDistrict(addressComponent.getStr("district"));

            return geoResultVO;
        });
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.BAIDU;
    }

    @Override
    public int getOrder() {
        return EagleOrdered.TWO;
    }
}
