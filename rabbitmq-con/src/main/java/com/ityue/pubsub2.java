package com.ityue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class pubsub2 {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/ityueca");
        factory.setUsername("ityue");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName1 = "test_queue1";
        String queueName2 = "test_queue2";
        Consumer consumer  = new DefaultConsumer(channel){
            //回调方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body:"+new String(body));
            }
        };
        channel.basicConsume(queueName2,true,consumer);
    }
}
