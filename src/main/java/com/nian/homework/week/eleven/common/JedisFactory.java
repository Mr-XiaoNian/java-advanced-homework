package com.nian.homework.week.eleven.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

@Service
public class JedisFactory {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;


    @Bean
    public JedisPool JedisPoolFactory() {
        return new JedisPool(redisHost, redisPort);
    }


}
