package com.nian.homework.week.seven;

import com.alibaba.fastjson.JSONObject;
import com.nian.homework.util.TimeUtil;
import com.nian.homework.week.five.jdbc.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@RestController
public class InsertTestController {

    @Autowired
    private JDBCUtil jdbcUtil;


    public static void main(String[] args) throws InterruptedException {
    }


    @GetMapping("test")
    public String test(){
        Connection conn = jdbcUtil.getConnection();
        System.out.println(TimeUtil.getLocalTimeBySyncDefault(new Date()));
        String sql = "insert into user(id,name,tel,email,create_time) values('uuid0','jl','176','nian','"+ TimeUtil.getLocalTimeBySyncDefault(new Date()) +"')";
        System.out.println(sql);
        jdbcUtil.executeNonQuery(conn, sql);
        return "success";
    }

    @GetMapping("/insertOneByOne")
    public void insertOneByOne() {
        Connection conn = jdbcUtil.getConnection();
        List<String> list = new ArrayList<>();
        StringBuilder sb;
        for (int i = 0; i < 1000000; i++) {
            sb = new StringBuilder();
            sb.append("insert into user(id,name,tel,email,create_time) values('");
            sb.append(UUID.randomUUID()).append("','");
            sb.append("jl','");
            sb.append(i).append("','");
            sb.append("nian','");
            sb.append(TimeUtil.getLocalTimeBySyncDefault(new Date())).append("')");
            list.add(sb.toString());
        }
        long startTime = new Date().getTime();
        System.out.println("start time:" + startTime);
        for (String eachSql : list) {
            jdbcUtil.executeNonQuery(conn, eachSql);
        }
        long endTime = new Date().getTime();
        System.out.println("end time:" + endTime);
        System.out.println("use time:" + (endTime - startTime)/3600);
    }

    @GetMapping("/insertBatch")
    public void insertBatch() throws SQLException {
        Connection conn = jdbcUtil.getConnection();



        long startTime = new Date().getTime();
        System.out.println("start time:" + startTime);
        conn.setAutoCommit(false);


//        String sql = "insert into order(id,order_number,user_id,address,product,product_sku,nums,total_price,status,create_time) values";


        String sql = "insert into student(name) values(?) ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for(int i = 0; i < 100000; i++){
            preparedStatement.setString(1, String.valueOf("666"));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.commit();


//        Statement statement = conn.createStatement();
//        for(int i = 0; i < 100000; i = i+1000){
//            statement.addBatch(" insert into student(name) values('666')");
//            if (i %5000 ==0) {
//                statement.executeBatch();
//            }
//        }
//        conn.commit();


//        String sql = "insert into student(name) values";
//        Statement statement = conn.createStatement();
//        for(int i = 0; i < 100000; i = i+1000){
//            StringBuilder sb = new StringBuilder();
//            sb.append(sql);
//            for (int j = 0; j < 1000; j++) {
//                sb.append("(").append(i).
//                        append("),");
//            }
//            sb.deleteCharAt(sb.length() -1);
//            statement.addBatch(sb.toString());
//        }
//        statement.executeBatch();
//        conn.commit();






        long endTime = new Date().getTime();
        System.out.println("end time:" + endTime);
        System.out.println("use time:" + (endTime - startTime)/3600);

        jdbcUtil.free(null, preparedStatement, conn);

    }

}
