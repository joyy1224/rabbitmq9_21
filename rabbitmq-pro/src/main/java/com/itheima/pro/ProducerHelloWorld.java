package com.itheima.pro;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ProducerHelloWorld {
    public static void main(String[] args) throws IOException, TimeoutException {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/ityueca");
        factory.setUsername("ityue");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare("work_ques",true,false,false,null);

        for (int i = 0; i < 100; i++) {
            String body = "hello rabbitmq....." + i;
            //发送消息
            channel.basicPublish("","work_ques",null,body.getBytes(StandardCharsets.UTF_8));
        }

        channel.close();
        connection.close();

    }
}
