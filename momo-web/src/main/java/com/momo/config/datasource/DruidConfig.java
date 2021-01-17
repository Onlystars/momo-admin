package com.momo.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * FileName: DruidConfig
 * Author: zipeng Li
 * Date: 2021/1/17  21:08
 * Description: 数据源配置类
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号       描述
 */
@Slf4j
@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }
}
