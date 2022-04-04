package com.itheima.em.server.service.impl.baidu;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.itheima.em.config.BaiduServerConfig;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.service.EagleOrdered;
import com.itheima.em.service.IPService;
import com.itheima.em.util.Constants;
import com.itheima.em.vo.IpResultVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("BaiduIPService")
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduIPServiceImpl implements IPService {

    @Resource
    private BaiduWebApiService baiduWebApiService;
    @Resource
    private EagleConfig eagleConfig;

    @Override
    public int getOrder() {
        return EagleOrdered.TWO;
    }

    /**
     * 百度查询IPv6需要提交工单申请
     *
     * @param ip   ip值，如：140.206.149.83
     * @param type IP类型,值为 4 或 6，4 表示 IPv4，6 表示 IPv6
     * @return
     */
    @Override
    public IpResultVo query(String ip, int type) {
        String url = this.eagleConfig.getBaiduWebApi() + "/location/ip";
        //封装请求参数
        Map<String, Object> param = new HashMap<>();
        param.put("ip", ip);
        param.put("coor", Constants.GCJ02);
        return this.baiduWebApiService.doGet(url, param, response -> {
            if (!response.isOk()) {
                return null;
            }
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (json.getInt("status") != 0) {
                return null;
            }

            IpResultVo ipResultVo = new IpResultVo();

            //解析数据
            JSONObject content = json.getJSONObject("content");
            JSONObject addressDetail = content.getJSONObject("address_detail");
            ipResultVo.setCity(addressDetail.getStr("city"));
            ipResultVo.setDistrict(addressDetail.getStr("district"));
            ipResultVo.setProvince(addressDetail.getStr("province"));

            JSONObject point = content.getJSONObject("point");
            ipResultVo.setLocation(point.getStr("x") + "," + point.getStr("y"));
            ipResultVo.setIp(ip);
            return ipResultVo;
        });
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.BAIDU;
    }
}
