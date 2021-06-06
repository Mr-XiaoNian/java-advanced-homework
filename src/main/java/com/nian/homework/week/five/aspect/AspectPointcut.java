package com.nian.homework.week.five.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectPointcut {

    //在SpringConfigInitial里项目启动后通过applicationContext获取了切面，调用切面方法
    @Pointcut(value = "execution(* com.nian.homework.week.five.aspect.AspectEntity.testAop(..))")
    public void point(){
    }


    @Before(value = "point()")
    public void before(){
        System.out.println("before target method");
    }


    @Around(value = "point()")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("around begin target method");
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after target method");
    }


    @AfterReturning(value = "point()")
    public void after(){
        System.out.println("after target method");
    }


}
