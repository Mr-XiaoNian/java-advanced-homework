package com.nian.homework.week.eleven.pubsub.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
public class SubscribeController {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SubscribeService subscribeService;

    @GetMapping("/subscribe")
    public String subscribe(String topic) {
        subscribeService.subscribeTopic(jedisPool.getResource(), new JedisPubSubListener(), topic);
        return "发起对" + topic + "的订阅";
    }
}
