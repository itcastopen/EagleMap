package com.itheima.em.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("EagleMap接口文档")
                        .description("EagleMap是一个功能强大、简单易用、支持多地图服务商的地图中台服务系统。")
                        .termsOfServiceUrl("http://www.itcast.cn/")
                        .contact(new Contact("传智教育·研究院", "http://www.itcast.cn/", "yjy@itcast.cn"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("cn.itcast.em.api.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}