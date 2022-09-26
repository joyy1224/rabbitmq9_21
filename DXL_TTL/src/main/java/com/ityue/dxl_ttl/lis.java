package com.ityue.dxl_ttl;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "queue_ttl")
public class lis {
   @RabbitHandler(isDefault = true)
    public void onms(Message message, Channel channel){
       System.out.println(message);
       try {
           channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
