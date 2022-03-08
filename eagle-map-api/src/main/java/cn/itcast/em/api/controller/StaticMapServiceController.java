package cn.itcast.em.api.controller;

import cn.hutool.core.util.StrUtil;
import cn.itcast.em.api.vo.StaticMapParam;
import cn.itcast.em.service.StaticMapService;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/static/map")
public class StaticMapServiceController extends BaseController<StaticMapService> {

    /**
     * 根据坐标地图查询地图静态图
     *
     * @param staticMapParam
     * @return
     */
    @PostMapping
    public R<String> query(@RequestBody StaticMapParam staticMapParam) {
        String result = super.chooseService(staticMapParam.getProvider())
                .query(staticMapParam.getLocation(), staticMapParam.getWidth(),
                        staticMapParam.getHeight(), staticMapParam.getZoom(), staticMapParam.getParam());
        if (StrUtil.isNotEmpty(result)) {
            return R.success(result);
        }
        return R.error("Query failed.");
    }

}
