package com.itheima.em.config;

import lombok.Data;

@Data
public class AdminConfig {

    private String user;
    private String password;
    private String privateKey;
    private String publicKey;
    private Integer tokenTime;

}
