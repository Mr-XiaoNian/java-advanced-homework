package com.nian.homework.week.eleven.pubsub.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
public class PublishController {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private PublishService publishService;

    @GetMapping("/publish")
    public String publish(String topic, String message) {
        publishService.publishTopic(jedisPool.getResource(),  topic, message);
        return "在" + topic +"上发布消息" + message;
    }
}
