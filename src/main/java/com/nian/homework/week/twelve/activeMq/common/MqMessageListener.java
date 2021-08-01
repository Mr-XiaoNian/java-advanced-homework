package com.nian.homework.week.twelve.activeMq.common;

import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MqMessageListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
     try {
         ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
         String content = activeMQTextMessage.getText();
         System.out.println("收到的消息:" + content);
     } catch (Exception e) {
         e.printStackTrace();
     }
    }


}
