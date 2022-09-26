package com.ityue.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;

@Configuration
public class RabbitmqConfig {

    public static final String EXCHANGE_NAME = "boot_topic_exchange";
    public static final String QUEUE_NAME = "boot_topic_exchange";

    //交换机
    @Bean("bootExchange")
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }
    //队列
    @Bean("bootQueue")
    public Queue bootQueue(){
        return new Queue(QUEUE_NAME);
    }
    //绑定
    @Bean
    public Binding binding(@Qualifier("bootExchange") Exchange exchange, @Qualifier("bootQueue")Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}
