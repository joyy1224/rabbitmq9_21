package com.ityue.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareBatchMessageListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class listener implements ChannelAwareMessageListener {
    int i = 0;
    @Override
    @RabbitListener(queues = {"test_queue_confirm"})
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
//        System.err.println(message);
        System.err.println("处理。。。。。。。");
        int i = 3/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (Exception e) {
//            e.printStackTrace();
//            Thread.sleep(2*1000);
            System.err.println("拒绝接收"+ ++i);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,false);

        }
    }

//    @Override
//    @RabbitListener(queues = {"queue_confirm"})
//    public void onMessage(Message message) {
//        System.err.println("自动签收"+message);
//    }

//    @RabbitListener(queues = {"queue_confirm"})
//    public void listener1(Message message){
//        System.err.println(message);
//    }
}
