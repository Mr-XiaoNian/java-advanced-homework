package com.nian.homework.week.eleven.pubsub.subscribe;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class SubscribeService {

    public void subscribeTopic(Jedis jedis, JedisPubSubListener jedisPubSubListener, String topic) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               jedis.subscribe(jedisPubSubListener, topic);
           }
       });
    }

}
