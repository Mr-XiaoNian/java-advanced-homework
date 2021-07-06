package com.nian.homework.week.nine.core.service.impl;


import com.nian.homework.week.nine.core.model.Order;
import com.nian.homework.week.nine.core.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "currentTime:" + System.currentTimeMillis(), 9.9f);
    }
}
