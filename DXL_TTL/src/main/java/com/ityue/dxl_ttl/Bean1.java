package com.ityue.dxl_ttl;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class Bean1 {
    @Bean("test_queue_ttl")
    public Queue test_queue(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange","exchange_ttl");
        map.put("x-dead-letter-routing-key","ttl.hh");
        map.put("x-message-ttl",10000);
        return new Queue("test_queue_ttl",true,false,false,map);
    }

    @Bean("test_exchange_ttl")
    public Exchange test_Exchange(){
        return ExchangeBuilder.topicExchange("test_exchange_ttl").build();
    }

    @Bean
    public Binding test_Bing(@Qualifier("test_exchange_ttl") Exchange exchange, @Qualifier("test_queue_ttl")Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("test.#").noargs();
    }
    @Bean("queue_ttl")
    public Queue queue(){
        return new Queue("queue_ttl");
    }

    @Bean("exchange_ttl")
    public Exchange Exchange(){
        return ExchangeBuilder.topicExchange("exchange_ttl").build();
    }

    @Bean
    public Binding Bing(@Qualifier("exchange_ttl") Exchange exchange, @Qualifier("queue_ttl")Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("ttl.#").noargs();
    }
}
