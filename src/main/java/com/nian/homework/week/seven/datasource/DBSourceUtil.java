package com.nian.homework.week.seven.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;



//根据dbName返回对应JdbcTemplate
@Service
public class DBSourceUtil {

//    @Autowired
//    @Qualifier( "masterJdbcTemplate")
//    private  JdbcTemplate jdbcTemplate3306;
//
//
//    @Autowired
//    @Qualifier( "slave1JdbcTemplate")
//    private  JdbcTemplate jdbcTemplate3316;
//
//
//    @Autowired
//    @Qualifier( "slave2JdbcTemplate")
//    private  JdbcTemplate jdbcTemplate3326;


//    public  JdbcTemplate getJdbcTemplate(String dbName ) {
//        switch (dbName) {
//            case "slave1": {
//                return jdbcTemplate3316;
//            }
//            case "slave2": {
//                return jdbcTemplate3326;
//            }
//            default:{
//                return jdbcTemplate3306;
//            }
//        }
//    }


}
