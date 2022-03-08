package cn.itcast.em.api.controller;

import cn.hutool.core.util.StrUtil;
import cn.itcast.em.api.vo.DirectionParam;
import cn.itcast.em.service.DirectionService;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/direction")
public class DirectionController extends BaseController<DirectionService> {

    /**
     * 驾车路线规划
     *
     * @param directionParam
     * @return
     */
    @PostMapping("driving")
    public R<String> driving(@RequestBody DirectionParam directionParam) {
        //选择地图服务商
        DirectionService directionService = super.chooseService(directionParam.getProvider());
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
    @PostMapping("walking")
    public R<String> walking(@RequestBody DirectionParam directionParam) {
        //选择地图服务商
        DirectionService directionService = super.chooseService(directionParam.getProvider());
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
    @PostMapping("bicycling")
    public R<String> bicycling(@RequestBody DirectionParam directionParam) {
        //选择地图服务商
        DirectionService directionService = super.chooseService(directionParam.getProvider());
        String drivingResult = directionService.bicycling(directionParam.getOrigin(),
                directionParam.getDestination(), directionParam.getParam());
        if (StrUtil.startWithIgnoreCase(drivingResult, "error")) {
            return R.error(drivingResult);
        }
        return R.success(drivingResult);
    }

}
