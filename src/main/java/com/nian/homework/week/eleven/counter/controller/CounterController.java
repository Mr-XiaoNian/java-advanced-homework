package com.nian.homework.week.eleven.counter.controller;

import com.nian.homework.week.eleven.counter.DistributedCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;


@RestController
public class CounterController {

    private static final String CK = "CalvinKlein";

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private DistributedCounter distributedCounter;

    @GetMapping("/sellStock")
    public String sellStock() {
        boolean result = distributedCounter.sellStock(jedisPool.getResource(), CK);
        if (result) {
            return "success";
        } else {
            return "false";
        }
    }

}
