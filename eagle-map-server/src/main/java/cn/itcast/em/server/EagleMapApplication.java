package cn.itcast.em.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableRetry //启用重试机制
@EnableTransactionManagement
@ComponentScan("cn.itcast.em")
@SpringBootApplication
@EnableConfigurationProperties
public class EagleMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(EagleMapApplication.class, args);
    }
}
