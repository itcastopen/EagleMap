package com.itheima.em.api.controller;

import com.itheima.em.enums.ProviderType;
import com.itheima.em.service.IPService;
import com.itheima.em.service.impl.EagleMapServiceFactory;
import com.itheima.em.vo.IpResultVo;
import com.itheima.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "地图基础服务")
@RestController
@RequestMapping("api/ip")
public class IPController extends BaseController {

    /**
     * ip定位
     *
     * @param ip   ip值，如：140.206.149.83
     * @param type IP类型,值为 4 或 6，4 表示 IPv4，6 表示 IPv6
     * @return
     */
    @ApiImplicitParams({@ApiImplicitParam(name = "ip", value = "IP地址，如：114.242.26.45", required = true),
            @ApiImplicitParam(name = "type", value = "IP类型,值为 4 或 6，4 表示 IPv4，6 表示 IPv6"),
            @ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图")})
    @ApiOperation(value = "IP定位", notes = "IP定位是一套简单的HTTP接口，根据用户输入的IP地址，能够快速的帮用户定位IP的所在位置。")
    @GetMapping
    public R<IpResultVo> query(@RequestParam("ip") String ip,
                               @RequestParam(value = "type", defaultValue = "4")
                                       Integer type,
                               @RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider) {
        IPService ipService = EagleMapServiceFactory.getService(provider, IPService.class);
        return R.success(ipService.query(ip, type));
    }

}
