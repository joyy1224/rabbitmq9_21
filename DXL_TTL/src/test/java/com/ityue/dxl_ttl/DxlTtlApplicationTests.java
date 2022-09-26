package com.ityue.dxl_ttl;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DxlTtlApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void TTL1() throws InterruptedException {
        rabbitTemplate.convertAndSend("test_exchange_ttl","test.hh","订单信息");
        for (int i = 0; i < 3; i++) {
            System.err.println(i);
            Thread.sleep(1000);
        }
    }
}

