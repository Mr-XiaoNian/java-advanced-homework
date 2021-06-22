package com.nian.homework.week.seven;


import com.nian.homework.util.TimeUtil;
//import com.nian.homework.week.five.jdbc.JDBCUtil;
import com.nian.homework.week.five.jdbc.JDBCUtil;
import com.nian.homework.week.seven.datasource.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

@RestController
public class DataSourceController {

    @Autowired
    DBUtil dbUtil;

    @Autowired
    JDBCUtil jdbcUtil;

    @GetMapping("dataSourceTest")
    public String dataSourceTest(@RequestParam String dbName) throws SQLException {
        JdbcTemplate jdbcTemplate;
        switch (dbName) {
            case "slave1":
            case "slave2": {
                jdbcTemplate = dbUtil.getJdbcTemplate(dbName);
                int random = new Random().nextInt(9999);
                jdbcTemplate.execute("insert into t1(id) values("+random+")");
                return "success";
            }
            case "master": {
                jdbcTemplate = dbUtil.getJdbcTemplate(dbName);
                int random = new Random().nextInt(9999);
                jdbcTemplate.execute("insert into `catalog`(id,name,parent_id,create_time) values('"+random+"','jk','0','"+ TimeUtil.getLocalTimeByDefault(new Date()) +"')");
                return "success";
            }
            default: {
                Connection conn = jdbcUtil.getConnection();
                int random = new Random().nextInt(9999);
                Statement st = conn.createStatement();
                st.execute("insert into `catalog`(id,name,parent_id,create_time) values('" + random + "','jk','0','" + TimeUtil.getLocalTimeByDefault(new Date()) + "')");
                st.close();
                conn.close();
                return "success";
            }
        }
    }

}
