package com.nian.homework.week.twelve.activeMq.common;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

@Component
public class MqConnection {

    private static String mqUrl;

    private static  String user;

    private static  String password ;

    private static ConnectionFactory connectionFactory;

    private static Connection connection;

    @Value("${spring.activemq.broker-url}")
    public void setMqUrl(String activeMqUrl){
        MqConnection.mqUrl = activeMqUrl;
    }

    @Value("${spring.activemq.user}")
    public void setMqUser(String user){
        MqConnection.user = user;
    }

    @Value("${spring.activemq.password}")
    public void setMqPassword(String password){
        MqConnection.password = password;
    }


    public static void initMqByQueue() {
        try {
            if (connectionFactory == null) {
                connectionFactory = new ActiveMQConnectionFactory(user, password, mqUrl);
                connection = connectionFactory.createConnection();
                connection.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        try {
            System.out.println(connection);
            return connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }




}
