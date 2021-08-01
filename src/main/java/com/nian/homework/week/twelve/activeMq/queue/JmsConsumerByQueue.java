package com.nian.homework.week.twelve.activeMq.queue;

import com.nian.homework.week.twelve.activeMq.common.MqConnection;
import com.nian.homework.week.twelve.activeMq.common.MqMessageListener;

import javax.jms.*;

public class JmsConsumerByQueue {


    public static void listenQueue(String queueName) {
        try {
            Session session = MqConnection.getSession();
            Destination destination = session.createQueue(queueName);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MqMessageListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
