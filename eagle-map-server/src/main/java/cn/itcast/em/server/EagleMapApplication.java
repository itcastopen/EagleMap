package cn.itcast.em.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan("cn.itcast.em")
@MapperScan("cn.itcast.em.mapper")
@SpringBootApplication
@EnableConfigurationProperties
public class EagleMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(EagleMapApplication.class, args);
    }
}
