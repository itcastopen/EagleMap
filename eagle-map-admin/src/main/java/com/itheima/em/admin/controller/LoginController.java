package com.itheima.em.admin.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.itheima.em.admin.annotation.NoAuthorization;
import com.itheima.em.admin.utils.JwtUtils;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.vo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 可视化管理系统的登录业务
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/18
 */
@RestController
@RequestMapping("sys/login")
public class LoginController {

    @Resource
    private EagleConfig eagleConfig;

    @NoAuthorization
    @PostMapping
    public R<String> login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        String user = this.eagleConfig.getAdmin().getUser();
        String pwd = this.eagleConfig.getAdmin().getPassword();

        if (StrUtil.equals(user, userName) && StrUtil.equals(pwd, DigestUtil.md5Hex(password))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("user", user);
            //token有效期为24小时
            String token = JwtUtils.createToken(claims,
                    this.eagleConfig.getAdmin().getPrivateKey(),
                    this.eagleConfig.getAdmin().getTokenTime());
            return R.success(token);
        }

        return R.error("用户名或密码错误.");

    }
}
