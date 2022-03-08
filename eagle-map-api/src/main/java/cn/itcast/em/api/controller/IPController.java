package cn.itcast.em.api.controller;

import cn.itcast.em.annotations.EagleAutowired;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.service.IPService;
import cn.itcast.em.vo.IpResultVo;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ip")
public class IPController extends BaseController<IPService> {

    /**
     * ip定位
     *
     * @param ip   ip值，如：140.206.149.83
     * @param type IP类型,值为 4 或 6，4 表示 IPv4，6 表示 IPv6
     * @return
     */
    @GetMapping
    public R<IpResultVo> query(@RequestParam("ip") String ip,
                               @RequestParam(value = "type", defaultValue = "4")
                                       Integer type,
                               @RequestParam(value = "provider", defaultValue = "NONE") ServerType provider) {
        return R.success(super.chooseService(provider).query(ip, type));
    }

}
