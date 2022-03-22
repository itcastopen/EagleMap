package cn.itcast.em.admin.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {

    /**
     * 生成token
     *
     * @param claims        token中存储的数据，请勿传入敏感数据
     * @param privateKeyStr RSA私钥字符串
     * @param hour          过期时间，单位为：小时
     * @return token字符串
     */
    public static String createToken(Map<String, Object> claims, String privateKeyStr, int hour) {
        RSA rsa = new RSA(privateKeyStr, null);

        Map<String, Object> header = new HashMap<String, Object>();
        header.put(JwsHeader.TYPE, JwsHeader.JWT_TYPE);
        header.put(JwsHeader.ALGORITHM, "RS256");

        // 生成token
        return Jwts.builder()
                .setHeader(header)  //header，可省略
                .setClaims(claims) //payload，存放数据的位置，不能放置敏感数据，如：密码等
                .signWith(SignatureAlgorithm.RS256, rsa.getPrivateKey()) //设置签名的加密算法和密钥
                .setExpiration(DateUtil.offsetHour(new Date(), hour)) //设置过期时间，{hour}小时后过期
                .compact();
    }

    /**
     * 校验token
     *
     * @param token        token字符串
     * @param publicKeyStr RSA公钥字符串
     * @return
     */
    public static Map<String, Object> checkToken(String token, String publicKeyStr) {
        RSA rsa = new RSA(null, publicKeyStr);
        try {
            // 通过token解析数据
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(rsa.getPublicKey()) //设置校验token签名的密钥
                    .parseClaimsJws(token)
                    .getBody();
            return body;
        } catch (ExpiredJwtException e) {
            // System.out.println("token已经过期！");
        } catch (Exception e) {
            log.error("token非法传入，token = {}", token, e);
        }
        return null;
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        System.out.println(rsa.getPublicKeyBase64());
        System.out.println("------------");
        System.out.println(rsa.getPrivateKeyBase64());
    }

}
