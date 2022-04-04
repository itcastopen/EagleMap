package com.itheima.em.api.controller;

import cn.hutool.core.util.StrUtil;
import com.itheima.em.api.vo.DirectionParam;
import com.itheima.em.service.DirectionService;
import com.itheima.em.service.impl.EagleMapServiceFactory;
import com.itheima.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "路线规划")
@RestController
@RequestMapping("api/direction")
public class DirectionController extends BaseController {

    /**
     * 驾车路线规划
     *
     * @param directionParam
     * @return
     */
    @ApiOperation(value = "驾车路线规划", notes = "驾车路径规划 API 可以规划以小客车、轿车通勤出行的方案，并且返回通勤方案的数据。")
    @PostMapping("driving")
    public R<String> driving(@RequestBody DirectionParam directionParam) {
        //选择地图服务商
        DirectionService directionService = EagleMapServiceFactory.getService(directionParam.getProvider(), DirectionService.class);
        String drivingResult = directionService.driving(directionParam.getOrigin(),
                directionParam.getDestination(), directionParam.getParam());
        if (StrUtil.startWithIgnoreCase(drivingResult, "error")) {
            return R.error(drivingResult);
        }
        return R.success(drivingResult);
    }

    /**
     * 步行路线规划
     *
     * @param directionParam
     * @return
     */
    @ApiOperation(value = "步行路线规划", notes = "步行路径规划 API 可以规划100KM以内的步行通勤方案，并且返回通勤方案的数据。")
    @PostMapping("walking")
    public R<String> walking(@RequestBody DirectionParam directionParam) {
        //选择地图服务商
        DirectionService directionService = EagleMapServiceFactory.getService(directionParam.getProvider(), DirectionService.class);
        String drivingResult = directionService.walking(directionParam.getOrigin(),
                directionParam.getDestination(), directionParam.getParam());
        if (StrUtil.startWithIgnoreCase(drivingResult, "error")) {
            return R.error(drivingResult);
        }
        return R.success(drivingResult);
    }


    /**
     * 骑行路线规划
     *
     * @param directionParam
     * @return
     */
    @ApiOperation(value = "骑行路线规划", notes = "骑行路径规划用于规划骑行通勤方案，规划时不会考虑路况；考虑天桥、单行线、封路等情况。")
    @PostMapping("bicycling")
    public R<String> bicycling(@RequestBody DirectionParam directionParam) {
        //选择地图服务商
        DirectionService directionService = EagleMapServiceFactory.getService(directionParam.getProvider(), DirectionService.class);
        String drivingResult = directionService.bicycling(directionParam.getOrigin(),
                directionParam.getDestination(), directionParam.getParam());
        if (StrUtil.startWithIgnoreCase(drivingResult, "error")) {
            return R.error(drivingResult);
        }
        return R.success(drivingResult);
    }

}
