package com.nian.homework.week.twelve.activeMq.queue;

import com.nian.homework.week.twelve.activeMq.common.MqConnection;

import javax.jms.*;


public class JmsProductByQueue {

    public static void sendMessage(String queueName, String messageContent) {
        try {
            Session session = MqConnection.getSession();
            Destination destination = session.createQueue(queueName);
            MessageProducer messageProducer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(messageContent);
            System.out.println("触发queue:"+queueName+"的生产消息");
            messageProducer.send(message);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
