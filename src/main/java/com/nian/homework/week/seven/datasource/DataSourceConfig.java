package com.nian.homework.week.seven.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mysqlDataSource3306(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave1")
    @Qualifier("slave1")
    @ConfigurationProperties(prefix = "spring.slave1.datasource")
    public DataSource mysqlDataSource3316(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave2")
    @Qualifier("slave2")
    @ConfigurationProperties(prefix = "spring.slave2.datasource")
    public DataSource mysqlDataSource3326(){
        return DataSourceBuilder.create().build();
    }


}
