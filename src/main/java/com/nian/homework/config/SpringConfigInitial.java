package com.nian.homework.config;

import com.nian.homework.week.three.complex.inbound.HttpInboundServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpringConfigInitial implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        //简单的nettyServer启动
        //NettyHttpServer.startNettyHttpServer(8805,2, 16);

        //不太好模拟多个服务启动，添加两个本地服务的host:port
        List<String> backends = new ArrayList<>();
        backends.add("http://127.0.0.1:8801");
        backends.add("http://127.0.0.1:8801");
        //复杂的nettyServer启动
        HttpInboundServer.startNettyHttpServer(8805,2, 16, backends);
    }
}
