package com.nian.homework.week.nine.core.service.impl;

import com.nian.homework.week.nine.core.spi.RpcfxResolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RpcfxResolverImpl implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object resolve(String serviceClass) {
        return this.applicationContext.getBean(serviceClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
