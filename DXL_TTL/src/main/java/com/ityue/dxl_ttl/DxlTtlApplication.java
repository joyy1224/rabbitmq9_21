package com.ityue.dxl_ttl;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class DxlTtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DxlTtlApplication.class, args);
    }

}
