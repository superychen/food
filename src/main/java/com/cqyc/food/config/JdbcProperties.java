package com.cqyc.food.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class JdbcProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
