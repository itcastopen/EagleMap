package cn.itcast.em.server.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置MP的分页插件
 */
@Configuration
@ConditionalOnProperty(name = "eagle.service-mode", havingValue = "COMPLETE")
public class MybatisPlusConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("cn.itcast.em.mapper");
        return mapperScannerConfigurer;
    }

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        GlobalConfig globalConfig = new GlobalConfig();

        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setIdType(IdType.ASSIGN_ID); //雪花id
        dbConfig.setTablePrefix("eagle_"); //表前缀
        globalConfig.setDbConfig(dbConfig);

        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);

        //设置枚举扫描包
        mybatisSqlSessionFactoryBean.setTypeEnumsPackage("cn.itcast.em.enums");

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        //在映射实体或者属性时，将数据库中表名和字段名中的下划线去掉，按照驼峰命名法映射
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);

        return mybatisSqlSessionFactoryBean;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

}
