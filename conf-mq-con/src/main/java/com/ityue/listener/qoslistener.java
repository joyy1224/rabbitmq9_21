package com.ityue.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//@RabbitListener()
//@RabbitListener(queues = {"queue_confirm"})
public class qoslistener {
//
    @RabbitHandler(isDefault = true)
    public void on(Message message, Channel channel) throws InterruptedException, IOException {
        System.err.println(new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }
}