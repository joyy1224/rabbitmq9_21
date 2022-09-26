package com.ityue;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ityue.config.RabbitmqConfig.EXCHANGE_NAME;

@SpringBootTest
class MqProApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"boot.hahaha","boot mq1.....");
    }

}
