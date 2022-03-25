package cn.itcast.em.api.controller;

import cn.hutool.core.map.MapUtil;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.service.GeoCodeService;
import cn.itcast.em.service.IPService;
import cn.itcast.em.service.impl.EagleMapServiceFactory;
import cn.itcast.em.vo.GeoResultVO;
import cn.itcast.em.vo.IpResultVo;
import cn.itcast.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "地图基础服务")
@RestController
@RequestMapping("api/geo")
public class GeoCodeController extends BaseController {

    /**
     * 地理编码
     *
     * @param provider 服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图
     * @param address  详细地址
     * @param param    可选参数，具体参考服务商文档
     * @return 位置的geo信息
     */
    @ApiImplicitParams({@ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图"),
            @ApiImplicitParam(name = "address", value = "详细地址", required = true),
            @ApiImplicitParam(name = "param", value = "可选参数，具体参考服务商文档：<br/>" +
                    "百度地图：https://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding<br/>" +
                    "高德地图：https://lbs.amap.com/api/webservice/guide/api/georegeo#geo")})
    @ApiOperation(value = "地理编码", notes = "将详细的结构化地址转换为经纬度坐标，例如：北京市昌平区回龙观街道传智播客办公楼")
    @GetMapping("code")
    public R<GeoResultVO> geoCode(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                  @RequestParam("address") String address,
                                  @RequestParam Map<String, Object> param) {
        GeoCodeService geoCodeService = EagleMapServiceFactory.getService(provider, GeoCodeService.class);
        MapUtil.removeAny(param, "provider", "address");
        return R.success(geoCodeService.geoCode(address, param));
    }

    /**
     * 逆地理编码
     *
     * @param provider  服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图
     * @param longitude 经度
     * @param latitude  维度
     * @param param     可选参数，具体参考服务商文档
     * @return 位置的geo信息
     */
    @ApiImplicitParams({@ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true),
            @ApiImplicitParam(name = "latitude", value = "维度", required = true),
            @ApiImplicitParam(name = "param", value = "可选参数，具体参考服务商文档：<br/>" +
                    "百度地图：https://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding<br/>" +
                    "高德地图：https://lbs.amap.com/api/webservice/guide/api/georegeo#regeo")})
    @ApiOperation(value = "逆地理编码", notes = "将经纬度转换为详细结构化的地址，且返回附近周边的POI、AOI信息。例如：116.343847,40.060539")
    @GetMapping("decode")
    public R<GeoResultVO> geoDecode(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                    @RequestParam("longitude") Double longitude,
                                    @RequestParam("latitude") Double latitude,
                                    @RequestParam Map<String, Object> param) {
        GeoCodeService geoCodeService = EagleMapServiceFactory.getService(provider, GeoCodeService.class);
        MapUtil.removeAny(param, "provider", "address");
        return R.success(geoCodeService.geoDecode(longitude, latitude, param));
    }

}
