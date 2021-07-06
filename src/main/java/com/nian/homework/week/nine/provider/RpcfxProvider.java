package com.nian.homework.week.nine.provider;

import com.nian.homework.week.nine.core.api.Server;
import com.nian.homework.week.nine.core.model.RpcfxRequest;
import com.nian.homework.week.nine.core.model.RpcfxResponse;
import com.nian.homework.week.nine.core.service.OrderService;
import com.nian.homework.week.nine.core.service.UserService;
import com.nian.homework.week.nine.core.service.impl.OrderServiceImpl;
import com.nian.homework.week.nine.core.service.impl.RpcfxResolverImpl;
import com.nian.homework.week.nine.core.service.impl.UserServiceImpl;
import com.nian.homework.week.nine.core.spi.RpcfxResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpcfxProvider {

    @Autowired()
    Server server;

    @Bean
    public Server createInvoker(@Autowired() RpcfxResolver resolver){
        return new Server(resolver);
    }

    @Bean
    public RpcfxResolver createResolver(){
        return new RpcfxResolverImpl();
    }

    @PostMapping("/rpcFxProvider")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        return server.invoke(request);
    }

    @Bean(name = "com.nian.homework.week.nine.core.service.UserService")
    public UserService createUserService(){
        return new UserServiceImpl();
    }

    @Bean(name = "com.nian.homework.week.nine.core.service.OrderService")
    public OrderService createOrderService(){
        return new OrderServiceImpl();
    }
}
