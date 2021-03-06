package com.itheima.em.admin.controller;

import com.itheima.em.admin.annotation.NoAuthorization;
import com.itheima.em.admin.vo.SystemConfig;
import com.itheima.em.config.AMapServerConfig;
import com.itheima.em.config.BaiduServerConfig;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统配置相关业务
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/18
 */
@RequestMapping("sys")
@RestController
public class SystemController {

    @Resource
    private EagleConfig eagleConfig;

    @GetMapping("config")
    public R<List<SystemConfig>> querySystemConfig() {
        List<SystemConfig> result = new ArrayList<>();

        AMapServerConfig aMapServerConfig = this.eagleConfig.getAmap();

        if (null != aMapServerConfig) {
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setEnable(aMapServerConfig.getEnable());
            systemConfig.setServerName(aMapServerConfig.getName());
            systemConfig.setServers(Arrays.asList(aMapServerConfig.getServer(),
                    aMapServerConfig.getBrowser()));
            result.add(systemConfig);
        }

        BaiduServerConfig baiduServerConfig = this.eagleConfig.getBaidu();

        if (null != baiduServerConfig) {
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setEnable(baiduServerConfig.getEnable());
            systemConfig.setServerName(baiduServerConfig.getName());
            systemConfig.setServers(Arrays.asList(baiduServerConfig.getServer(),
                    baiduServerConfig.getBrowser()));
            result.add(systemConfig);
        }

        return R.success(result);
    }

    /**
     * 查询ak
     *
     * @param provider
     * @return
     */
    @GetMapping("ak")
    @NoAuthorization
    public R<String> querySystemConfig(@RequestParam("provider") ProviderType provider) {
        switch (provider) {
            case BAIDU: {
                return R.success(this.eagleConfig.getBaidu().getBrowser().getAk());
            }
            case AMAP: {
                return R.success(this.eagleConfig.getAmap().getBrowser().getAk());
            }
            default: {
                return R.success("");
            }
        }
    }


}
