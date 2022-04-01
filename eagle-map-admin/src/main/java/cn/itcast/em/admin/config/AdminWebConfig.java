package cn.itcast.em.admin.config;

import cn.itcast.em.admin.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {


    @Autowired
    private UserInterceptor userInterceptor;


    public void addInterceptors(InterceptorRegistry registry) {
        // 注意拦截器的顺序
        registry.addInterceptor(this.userInterceptor).addPathPatterns("/sys/**");
    }

    /**
     * 扩展mvc框架的消息转换器
     *
     * @param converters 转换器
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0, messageConverter);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/");
            Set<ErrorPage> errorPages = new HashSet<>();
            errorPages.add(error404Page);
            factory.setErrorPages(errorPages);
        };
    }
}