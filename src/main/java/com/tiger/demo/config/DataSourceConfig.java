package com.tiger.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

@Configuration
public class DataSourceConfig {

    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master(){
        DruidDataSource master =  new DruidDataSource();
        return master;
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave(){
        DruidDataSource slave =  new DruidDataSource();
        return slave;
    }

    @Bean("impala")
    @ConfigurationProperties(prefix = "spring.datasource.impala")
    public DataSource impala(){
        DruidDataSource impala =  new DruidDataSource();
        return impala;
    }

}
