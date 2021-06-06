package com.nian.homework.week.five.aspect;


import org.springframework.stereotype.Component;
@Component
public class AspectEntity {
    public void testAop(){
        System.out.println("this is target method");
    }
}
