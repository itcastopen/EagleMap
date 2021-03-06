package com.itheima.em.api.controller;

import cn.hutool.core.util.StrUtil;
import com.itheima.em.api.vo.StaticMapParam;
import com.itheima.em.service.StaticMapService;
import com.itheima.em.service.impl.EagleMapServiceFactory;
import com.itheima.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "地图基础服务")
@RestController
@RequestMapping("api/static/map")
public class StaticMapServiceController extends BaseController {

    /**
     * 根据坐标地图查询地图静态图
     *
     * @param staticMapParam
     * @return
     */
    @ApiOperation(value = "查询地图静态图", notes = "根据参数查询地图的静态图，响应图片的base64数据。")
    @PostMapping
    public R<String> query(@RequestBody StaticMapParam staticMapParam) {
        StaticMapService staticMapService = EagleMapServiceFactory.getService(staticMapParam.getProvider(), StaticMapService.class);
        String result = staticMapService.query(staticMapParam.getLocation(), staticMapParam.getWidth(),
                staticMapParam.getHeight(), staticMapParam.getZoom(), staticMapParam.getParam());
        if (StrUtil.isNotEmpty(result)) {
            return R.success(result);
        }
        return R.error("Query failed.");
    }

}
