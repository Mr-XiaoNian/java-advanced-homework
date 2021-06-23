package com.nian.homework.week.seven;

import com.nian.homework.week.five.jdbc.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@RestController
public class InsertTestController {

    @Autowired
    private JDBCUtil jdbcUtil;


    @GetMapping("/insertBatch")
    public String insertBatch() throws SQLException {

        Connection conn = jdbcUtil.getConnection();
        conn.setAutoCommit(false);

        long startTime = new Date().getTime();
        System.out.println("start time:" + startTime);

        //5000个execute,commit一次，25秒
        //10000个execute,commit一次，18秒
        //20000个execute,commit一次，17秒
        //22000个execute,commit一次，16秒
        //23000个execute,commit一次，19秒
        //25000个execute,commit一次，30秒
        //30000个execute,commit一次，47秒
        //50000个execute,commit一次，46秒
        String sql = "insert into `order`(id,order_number,user_id,address,product,product_sku,nums,total_price,status,create_time) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 1000000; i++) {
            preparedStatement.setString(1, String.valueOf(i));
            preparedStatement.setString(2, "666");
            preparedStatement.setString(3, "666");
            preparedStatement.setString(4, "666");
            preparedStatement.setString(5, "666");
            preparedStatement.setString(6, "666");
            preparedStatement.setInt(7, i);
            preparedStatement.setInt(8, i);
            preparedStatement.setString(9, "666");
            preparedStatement.setString(10, "2009-06-15 13:45:30");
            preparedStatement.addBatch();
            if (i % 23000 == 0) {
                preparedStatement.executeBatch();
                conn.commit();
                preparedStatement.clearBatch();
            }
        }
        preparedStatement.executeBatch();
        conn.commit();
        preparedStatement.clearBatch();


//        String sql = "insert into `order`(id,order_number,user_id,address,product,product_sku,nums,total_price,status,create_time) values";
//        Statement preparedStatement = conn.createStatement();
//        StringBuilder sb;
//        for (int i = 0; i < 500000; i = i+5000) {
//            sb = new StringBuilder();
//            sb.append(sql);
//            for (int j = 0; j < 5000; j++) {
//                sb.append("('")
//                        .append(i+j).append("','")
//                        .append(j).append("','")
//                        .append(j).append("','")
//                        .append(j).append("','")
//                        .append(j).append("','")
//                        .append(j).append("',")
//                        .append(j).append(",")
//                        .append(j).append(",'")
//                        .append(j).append("','2021-06-21 10:41:30'),");
//            }
//            sb.deleteCharAt(sb.length() - 1);
//            preparedStatement.addBatch(sb.toString());
//        }
//        preparedStatement.executeBatch();
//        conn.commit();
//        preparedStatement.clearBatch();



//        String sql = "insert into student(name) values(?) ";
//        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//        for(int i = 0; i < 100000; i++){
//            preparedStatement.setString(1, String.valueOf("666"));
//            preparedStatement.addBatch();
//        }
//        preparedStatement.executeBatch();
//        conn.commit();


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
        System.out.println("use time:" + (endTime - startTime) / 3600);

        jdbcUtil.free(null, preparedStatement, conn);
        return "success";
    }
}