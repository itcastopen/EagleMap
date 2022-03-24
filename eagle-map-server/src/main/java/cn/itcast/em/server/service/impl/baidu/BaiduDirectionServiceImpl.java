package cn.itcast.em.server.service.impl.baidu;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.service.DirectionService;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.vo.CoordinateVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度地图的实现类
 */
@Service("BaiduDirectionService")
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduDirectionServiceImpl implements DirectionService {

    @Resource
    private BaiduWebApiService baiduWebApiService;
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
        String url = this.eagleConfig.getBaiduWebApi() + "/directionlite/v1/driving";
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
        String url = this.eagleConfig.getBaiduWebApi() + "/directionlite/v1/walking";
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
        String url = this.eagleConfig.getBaiduWebApi() + "/directionlite/v1/riding";
        return this.execute(url, origin, destination, param);
    }

    @Override
    public int getOrder() {
        return EagleOrdered.TWO;
    }

    private String execute(String url, CoordinateVo origin, CoordinateVo destination, Map<String, Object> param) {
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("origin", origin.getLatitude() + "," + origin.getLongitude());
        requestParam.put("destination", destination.getLatitude() + "," + destination.getLongitude());
        if (CollUtil.isNotEmpty(param)) {
            //将用户的可选参数加入到请求参数列表中
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }
        //执行请求
        return this.baiduWebApiService.doGet(url, requestParam, response -> {
            if (!response.isOk()) {
                return null;
            }
            String body = response.body();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            if (jsonObject.getInt("status") != 0) {
                //出错，将错误码和错误信息返回
                String message = jsonObject.getStr("message");
                return StrUtil.format("error: message = {}", message);
            }

            return jsonObject.getJSONObject("result").toString();
        });
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.BAIDU;
    }
}
