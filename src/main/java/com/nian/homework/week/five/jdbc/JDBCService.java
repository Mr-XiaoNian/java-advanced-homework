package com.nian.homework.week.five.jdbc;//package com.nian.homework.week.five.jdbc;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.*;
//
//@Service
//public class JDBCService {
//
////    @Autowired
////    JDBCUtil jdbcUtil;
//
//    //使用事务
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
//    public void addStudent(String name) throws SQLException {
//        //将批处理的参数封装成List<LinkedHashSet<String>>,方便后面PrepareStatement赋值
//        List<LinkedHashSet<String>> sets = new ArrayList<>();
//        LinkedHashSet<String> set;
//        for (String eachName : name.split(",")) {
//            set = new LinkedHashSet<>();
//            set.add(eachName);
//            sets.add(set);
//        }
//
//        //获取Connection
//        Connection conn = jdbcUtil.getConnection();
//        //设置不自动提交,下面执行统一提交
//        conn.setAutoCommit(false);
//        try {
//            //批处理+PrepareStatement
//            String sql = "insert into student(name) values(?)";
//            jdbcUtil.executeBatch(conn, sql, sets);
//            //运行该错误会触发运行时异常
//            System.out.println(2 / 0);
//            jdbcUtil.executeNonQuery(conn, "insert into student(name) values(666)");
//            //统一commit
//            conn.commit();
//        } catch (Exception e) {
//            //出错回滚
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//}
