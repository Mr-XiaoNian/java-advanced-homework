package com.nian.homework.week.nine.core.service.impl;


import com.nian.homework.week.nine.core.model.User;
import com.nian.homework.week.nine.core.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "xiaonian" + System.currentTimeMillis());
    }
}
