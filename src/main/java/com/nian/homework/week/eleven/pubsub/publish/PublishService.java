package com.nian.homework.week.eleven.pubsub.publish;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class PublishService {

    public void publishTopic(Jedis jedis,  String topic, String message) {
        jedis.publish(topic, message);
    }

}
