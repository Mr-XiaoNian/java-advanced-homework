package com.nian.homework.week.twelve.activeMq.controller;


import com.nian.homework.week.twelve.activeMq.queue.JmsConsumerByQueue;
import com.nian.homework.week.twelve.activeMq.queue.JmsProductByQueue;
import com.nian.homework.week.twelve.activeMq.topic.JmsConsumerByTopic;
import com.nian.homework.week.twelve.activeMq.topic.JmsProductByTopic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActiveMqTestController {


    @GetMapping("/mqQueueProduct")
    public String mqQueueProduct(String queueName, String messageContent) {
        JmsProductByQueue.sendMessage(queueName,messageContent);
        return "success";
    }

    @GetMapping("/mqQueueConsumer")
    public String mqQueueConsumer(String queueName) {
        JmsConsumerByQueue.listenQueue(queueName);
        return "success";
    }

    @GetMapping("/mqTopicProduct")
    public String mqTopicProduct(String queueName, String messageContent) {
        JmsProductByTopic.sendMessage(queueName,messageContent);
        return "success";
    }

    @GetMapping("/mqTopicConsumer")
    public String mqTopicConsumer(String queueName) {
        JmsConsumerByTopic.listenQueue(queueName);
        return "success";
    }




}
