package com.ityue.Listener;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    @RabbitListener(queues = {"boot_topic_exchange"})
    public void ListenerQueue(Message message){
        System.err.println(message);
    }
}
