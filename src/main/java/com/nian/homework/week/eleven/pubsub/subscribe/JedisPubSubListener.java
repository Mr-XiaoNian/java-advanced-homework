package com.nian.homework.week.eleven.pubsub.subscribe;

import redis.clients.jedis.JedisPubSub;

public class JedisPubSubListener extends JedisPubSub {


    @Override
    public void onMessage(String channel, String message) {
        System.out.println("this topic:" + channel + ", receive message:" + message);
    }
}
