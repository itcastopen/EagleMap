package cn.itcast.em.server.service.impl.amap;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.AMapServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.service.DirectionService;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.vo.CoordinateVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 高德地图的实现类
 */
@Service("AMapDirectionService")
@ConditionalOnBean(AMapServerConfig.class)
public class AMapDirectionServiceImpl implements DirectionService {

    @Resource
    private AMapWebApiService aMapWebApiService;
    @Resource
    private EagleConfig eagleConfig;

    /**
     * 驾车路线规划
     *
     * @param origin      起点经纬度
     * @param destination 目的地经纬度
     * @param param       百度/高德的可选参数，如需要请根据官方文档添加参数
     *                    高德：https://lbs.amap.com/api/webservice/guide/api/newroute#t5
     *                    百度：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-0
     * @return
     */
    @Override
    public String driving(CoordinateVo origin, CoordinateVo destination, Map<String, Object> param) {
        String url = this.eagleConfig.getAmapWebApi() + "/v5/direction/driving";
        return this.execute(url, origin, destination, param);
    }

    /**
     * 步行路线规划
     *
     * @param origin      起点经纬度
     * @param destination 目的地经纬度
     * @param param       百度/高德的可选参数，如需要请根据官方文档添加参数
     *                    高德：https://lbs.amap.com/api/webservice/guide/api/newroute#t6
     *                    百度：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-2
     * @return
     */
    @Override
    public String walking(CoordinateVo origin, CoordinateVo destination, Map<String, Object> param) {
        String url = this.eagleConfig.getAmapWebApi() + "/v5/direction/walking";
        if (param == null) {
            param = new HashMap<>();
        }
        if (!param.containsKey("isindoor")) {
            try {
                param.put("isindoor", 0); //必填参数，是否需要室内算路, 0：不需要, 1：需要
            } catch (Exception e) {
                param = new HashMap<>();
                param.put("isindoor", 0);
            }
        }
        return this.execute(url, origin, destination, param);
    }

    /**
     * 骑行路线规划
     *
     * @param origin      起点经纬度
     * @param destination 目的地经纬度
     * @param param       百度/高德的可选参数，如需要请根据官方文档添加参数
     *                    高德：https://lbs.amap.com/api/webservice/guide/api/newroute#t7
     *                    百度：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-1
     * @return 百度/高德响应的数据
     */
    @Override
    public String bicycling(CoordinateVo origin, CoordinateVo destination, Map<String, Object> param) {
        String url = this.eagleConfig.getAmapWebApi() + "/v5/direction/bicycling";
        return this.execute(url, origin, destination, param);
    }

    @Override
    public int getOrder() {
        return EagleOrdered.ONE;
    }

    private String execute(String url, CoordinateVo origin, CoordinateVo destination, Map<String, Object> param) {
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("origin", origin.toParam());
        requestParam.put("destination", destination.toParam());
        if (CollUtil.isNotEmpty(param)) {
            //将用户的可选参数加入到请求参数列表中
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }
        //执行请求
        return this.aMapWebApiService.doGet(url, requestParam, response -> {
            if (!response.isOk()) {
                return null;
            }
            String body = response.body();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            if (jsonObject.getInt("status") != 1) {
                //出错，将错误码和错误信息返回
                String infoCode = jsonObject.getStr("infocode");
                String info = jsonObject.getStr("info");
                return StrUtil.format("error: infoCode = {}, info = {}", infoCode, info);
            }

            return body;
        });
    }
}
