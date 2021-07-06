package com.nian.homework.week.nine.core.model;

import lombok.Data;

@Data
public class User {
    public User(){}

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;
}
