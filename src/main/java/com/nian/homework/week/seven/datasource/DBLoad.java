package com.nian.homework.week.seven.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class DBLoad {

    @Primary
    @Bean(name = "masterJdbcTemplate")
    public JdbcTemplate jdbcTemplate3306(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "slave1JdbcTemplate")
    public JdbcTemplate jdbcTemplate3316(@Qualifier("slave1") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "slave2JdbcTemplate")
    public JdbcTemplate jdbcTemplate3326(@Qualifier("slave2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
