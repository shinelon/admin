package com.shinelon.demo.admin.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;

@Configuration
@MapperScan("com.shinelon.demo.admin.mapper*")
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "first.datasource")
    public DataSource dataSourceInit() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean fb = new MybatisSqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        fb.setTypeAliasesPackage("com.shinelon.demo.admin.entity");
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*/*.xml"));
        PaginationInterceptor pagination = new PaginationInterceptor();
        fb.setPlugins(new Interceptor[] { pagination });
        GlobalConfiguration gcf = new GlobalConfiguration();
        gcf.setDbType("jdbc:mysql:");
        gcf.setIdType(0);
        gcf.setDbColumnUnderline(true);
        fb.setGlobalConfig(gcf);
        return fb.getObject();
    }

    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
