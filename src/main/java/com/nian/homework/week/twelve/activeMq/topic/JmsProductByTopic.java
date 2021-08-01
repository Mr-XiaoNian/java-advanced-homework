package com.nian.homework.week.twelve.activeMq.topic;

import com.nian.homework.week.twelve.activeMq.common.MqConnection;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsProductByTopic {

    public static void sendMessage(String queueName, String messageContent) {
        try {
            Session session = MqConnection.getSession();
            Destination destination = session.createTopic(queueName);
            MessageProducer messageProducer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(messageContent);
            System.out.println("触发topic:"+queueName+"的生产消息");
            messageProducer.send(message);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
