package com.nian.homework.week.five.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class JDBCController {

    @Autowired
    JDBCService jdbcService;

    @GetMapping("addStudent")
    public String addStudent(@RequestParam String name) throws SQLException {
       try {
           jdbcService.addStudent(name);
           return "success";
       } catch (Exception e) {
           return "fail";
       }
    }
}
