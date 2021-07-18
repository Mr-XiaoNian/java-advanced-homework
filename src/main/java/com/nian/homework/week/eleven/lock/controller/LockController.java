package com.nian.homework.week.eleven.lock.controller;


import com.nian.homework.week.eleven.lock.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
public class LockController {

    @Autowired
    private DistributedLock distributedLock;

    @Autowired
    private JedisPool jedisPool;


    @GetMapping("/getLock")
    public boolean getLock(String id) {
        return distributedLock.lock(jedisPool.getResource(), id);
    }


    @GetMapping("/unLock")
    public boolean unLock(String id) {
        return distributedLock.unlock(jedisPool.getResource(), id);
    }



}
