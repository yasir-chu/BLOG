package com.chuyx.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author yasir.chu
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
