package cn.itcast.em.server.service.impl.amap;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.AMapServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.CoordinateType;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.service.CoordinateService;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.vo.CoordinateVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Struct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 高德地图的实现类
 */
@Service("AMapCoordinateService")
@ConditionalOnBean(AMapServerConfig.class)
public class AMapCoordinateServiceImpl implements CoordinateService {

    @Resource
    private EagleConfig eagleConfig;

    @Resource
    private AMapWebApiService aMapWebApiService;

    @Override
    public List<CoordinateVo> convertToGcj02(CoordinateType fromType, CoordinateVo... froms) {
        String url = this.eagleConfig.getAmapWebApi() + "/v3/assistant/coordinate/convert";
        //封装请求参数
        Map<String, Object> param = new HashMap<>();
        param.put("locations", CollUtil.join(Arrays.stream(froms).map(vo -> vo.getLongitude() + "," + vo.getLatitude())
                .collect(Collectors.toList()), ";"));
        param.put("coordsys", fromType.getaMapValue());
        param.put("to", CoordinateType.EAGLE.getBaiduValue());
        param.put("output", "JSON");

        return this.aMapWebApiService.doGet(url, param, response -> {
            if (!response.isOk()) {
                return Collections.emptyList();
            }
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 1) {
                return Collections.emptyList();
            }

            return StrUtil.split(json.getStr("locations"), ';')
                    .stream().map(s -> {
                        String[] array = StrUtil.splitToArray(s, ",");
                        return new CoordinateVo(Convert.toDouble(array[0]), Convert.toDouble(array[1]));
                    }).collect(Collectors.toList());
        });
    }

    @Override
    public CoordinateVo convert(CoordinateVo from, CoordinateType fromType, CoordinateType toType) {
        if (!StrUtil.equals(toType.getaMapValue(), CoordinateType.EAGLE.getaMapValue())) {
            throw new RuntimeException("Amap only supports conversion to gcj02 coordinate system.");
        }
        String url = this.eagleConfig.getAmapWebApi() + "/v3/assistant/coordinate/convert";
        //封装请求参数
        Map<String, Object> param = new HashMap<>();
        param.put("locations", from.getLongitude() + "," + from.getLatitude());
        param.put("coordsys", fromType.getaMapValue());
        param.put("to", CoordinateType.EAGLE.getBaiduValue());
        param.put("output", "JSON");

        return this.aMapWebApiService.doGet(url, param, response -> {
            if (!response.isOk()) {
                return null;
            }
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 1) {
                return null;
            }

            return StrUtil.split(json.getStr("locations"), ';')
                    .stream().map(s -> {
                        String[] array = StrUtil.splitToArray(s, ",");
                        return new CoordinateVo(Convert.toDouble(array[0]), Convert.toDouble(array[1]));
                    }).collect(Collectors.toList()).get(0);
        });
    }

    @Override
    public int getOrder() {
        return EagleOrdered.TWO;
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.AMAP;
    }
}
