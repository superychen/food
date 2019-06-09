package com.cqyc.food.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Configuration
@EnableConfigurationProperties(JdbcProperties.class)
public class MybatisPlusConfig {


    /**
     * 配置阿里巴巴数据源
     */
    @Bean
    public DataSource dataSource(JdbcProperties jdbcProperties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setPassword(jdbcProperties.getPassword());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        return dataSource;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
