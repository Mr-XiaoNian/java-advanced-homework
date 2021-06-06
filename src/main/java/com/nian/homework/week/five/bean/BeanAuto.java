package com.nian.homework.week.five.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//Component注解自动装配第一个BeanAuto，默认id是类名首字母小写beanAuto
@Component()
public class BeanAuto {


    //脱离componentScan依赖，独立的用bean注解手动进行bean的装配
    @Bean(name = "beanManual")
    private BeanManual beanManual(){
        return new BeanManual();
    }

    public BeanAuto() {
        System.out.println("BeanAuto will be load!");
    }
}
