package com.ityue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class conHello {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/ityueca");
        factory.setUsername("ityue");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        Consumer consumer  = new DefaultConsumer(channel){
            //回调方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //当收到消息会自动执行该方法
//                System.out.println("con1"+consumerTag);
//                System.out.println("getExchange1"+envelope.getExchange());
//                System.out.println("con1"+envelope.getRoutingKey());
//                System.out.println("properties1"+properties);
                System.out.println("body1"+new String(body));
            }
        };
        channel.basicConsume("work_ques",true,consumer);
    }
}
