package com.nian.homework.week.seven.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class DBUtil {


    @Qualifier( "masterJdbcTemplate")
    @Autowired
    private  JdbcTemplate jdbcTemplate3306;
    @Qualifier( "slave1JdbcTemplate")
    @Autowired
    private  JdbcTemplate jdbcTemplate3316;
    @Qualifier( "slave2JdbcTemplate")
    @Autowired
    private  JdbcTemplate jdbcTemplate3326;


    public  JdbcTemplate getJdbcTemplate(String dbName ) {
        switch (dbName) {
            case "slave1": {
                return jdbcTemplate3316;
            }
            case "slave2": {
                return jdbcTemplate3326;
            }
            default:{
                return jdbcTemplate3306;
            }
        }
    }


}
