package com.nian.homework.week.twelve.activeMq.topic;

import com.nian.homework.week.twelve.activeMq.common.MqConnection;
import com.nian.homework.week.twelve.activeMq.common.MqMessageListener;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class JmsConsumerByTopic {
    public static void listenQueue(String queueName) {
        try {
            Session session = MqConnection.getSession();
            Destination destination = session.createTopic(queueName);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MqMessageListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
