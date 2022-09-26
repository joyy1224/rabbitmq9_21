package com.ityue;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class raconfig {

    public final static String EX = "exchange_confrim";
    public final static String QU = "queue_confirm";
//
    @Bean("test_exchange_confrim")
    public Exchange test_exchange(){
        return ExchangeBuilder.topicExchange("test_exchange_confrim").build();
    }


    @Bean("test_queue_confirm")
    public Queue test_queue(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange","exchange_confrim");
        map.put("x-dead-letter-routing-key","confirm.hh");
        map.put("x-message-ttl",10000);
        map.put("x-max-length",10);
        return new Queue("test_queue_confirm",true,false,false,map);
    }

    @Bean
    public Binding test_binding(@Qualifier("test_exchange_confrim") Exchange exchange, @Qualifier("test_queue_confirm") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("test_confirm.#").noargs();
    }
    @Bean("exchange_confrim")
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange("exchange_confrim").build();
    }


    @Bean("queue_confirm")
    public Queue queue(){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("x-message-ttl",10000);
        return new Queue("queue_confirm");
    }

    @Bean
    public Binding binding(@Qualifier("exchange_confrim") Exchange exchange, @Qualifier("queue_confirm") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("confirm.#").noargs();
    }
}
