package com.itheima.pro;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Topics {
    public static void main(String[] args) throws IOException, TimeoutException {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/ityueca");
        factory.setUsername("ityue");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

//        DIRECT("direct"), FANOUT("fanout"), TOPIC("topic"), HEADERS("headers");
        String exchangeName = "test_Topics";
        //创建交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,false,null);
        String queueName1 = "test_Topics1";
        String queueName2 = "test_Topics2";
        //创建队列
        channel.queueDeclare(queueName1,true,false,false,null);
        channel.queueDeclare(queueName2,true,false,false,null);
//        绑定队列与交换机
        channel.queueBind(queueName1,exchangeName,"#.error");
        channel.queueBind(queueName1,exchangeName,"order.*");
        channel.queueBind(queueName2,exchangeName,"*.*");

        String body = "张三调用了 findall";
        channel.basicPublish(exchangeName,"order.info",null,body.getBytes(StandardCharsets.UTF_8));
//        channel.basicPublish(exchangeName,"error",null,"线上重大错误已被你解决".getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
