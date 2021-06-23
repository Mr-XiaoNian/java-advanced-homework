package com.nian.homework.week.seven;


//import com.nian.homework.week.five.jdbc.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

        import java.sql.SQLException;
        import java.util.Random;

@RestController
public class DataSourceController {

    //获取不同数据库的JdbcTemplate
    //@Autowired
    //DBSourceUtil dbSourceUtil;

    //获取原生jdbc的conn
    //@Autowired
    //JDBCUtil jdbcUtil;

    //shardingsphere的jdbc
    @Autowired
    JdbcTemplate jdbcTemplate;


    //配合根据不用DBName多数据源切换的测试方法
    @GetMapping("testForDataSource")
    public String testForDataSource(@RequestParam String dbName) throws SQLException {
//        JdbcTemplate jdbcTemplate = null;
//        switch (dbName) {
//            case "slave1":
//            case "slave2": {
//                jdbcTemplate = dbUtil.getJdbcTemplate(dbName);
//                int random = new Random().nextInt(9999);
//                //jdbcTemplate.execute("insert into t2(id) values("+random+")");
//                return "success";
//            }
//
//            default: {
//                jdbcTemplate = dbUtil.getJdbcTemplate(dbName);
//                int random = new Random().nextInt(9999);
//                jdbcTemplate.execute("insert into t2(id) values("+random+")");
//                return "success";
//            }
//        }
        return "success";
    }

    //配合测试ShardingSphere
    @GetMapping("testForShardingSphere")
    public String testForShardingSphere() throws SQLException {
        int random = new Random().nextInt(9999);
        jdbcTemplate.execute("insert into t2(id) values("+random+")");
        return "success";
    }


}
