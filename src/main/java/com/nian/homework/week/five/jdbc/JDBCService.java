package com.nian.homework.week.five.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class JDBCService {

    @Autowired
    JDBCUtil jdbcUtil;

    //使用事务
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
    public void addStudent(String name) throws SQLException {
        //将参数封装成List<LinkedHashSet<String>>,方便后面PrepareStatement赋值
        List<LinkedHashSet<String>> sets = new ArrayList<>();
        LinkedHashSet<String> set;
        for (String eachName : name.split(",")) {
            set = new LinkedHashSet<>();
            set.add(eachName);
            sets.add(set);
        }

        String sql = "insert into student(name) values(?)";
        //批处理+PrepareStatement
        jdbcUtil.executeBatch(sql, sets);
    }

}
