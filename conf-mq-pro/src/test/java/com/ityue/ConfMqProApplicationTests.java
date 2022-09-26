package com.ityue;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ityue.raconfig.EX;

@SpringBootTest
class ConfMqProApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
//        (RabbitTemplate.ConfirmCallback confirmCallback
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.err.println("confirm 被执行咯");
                if (ack)
                    System.err.println("成功原因"+cause);
                else
                    System.err.println("失败原因："+cause);
            }
        });
        rabbitTemplate.convertAndSend(EX,"confirm","message..........");
//        rabbitTemplate.convertAndSend("EX","confirm","message..........");
    }

    @Test
    void contextLoad() {

        rabbitTemplate.setMandatory(true);

//        (RabbitTemplate.ConfirmCallback confirmCallback
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * @param message 消息对象
             * @param replyCode 错误码
             * @param replyText 错误信息
             * @param exchange 交换机
             * @param routingKey 路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.err.println("return执行了.......");
                System.err.println("错误对象:"+message);
                System.err.println("错误码:"+replyCode);
                System.err.println("错误信息:"+replyText);
                System.err.println("交换机:"+exchange);
                System.err.println("路由键:"+routingKey);
            }
        });

    }
    @Test
    public void test1(){
        for (int i = 0; i < 10; i++) {
//            Message message = new Message("Message".getBytes(StandardCharsets.UTF_8),new MessageProperties());
            rabbitTemplate.convertAndSend(EX,"confirm","message");
        }
    }

    /**
     * ttl
     * 队列统一过期
     */
    @Test
    public void TTL(){
//        for (int i = 0; i < 10; i++) {
////            Message message = new Message("Message".getBytes(StandardCharsets.UTF_8),new MessageProperties());
//            rabbitTemplate.convertAndSend(EX,"confirm","message");
//        }

        MessagePostProcessor message  = new org.springframework.amqp.core.MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        };

        rabbitTemplate.convertAndSend(EX,"confirm","maggttttt....",message);
    }
    @Test
    public void TTL1(){
        //测试过期时间
//       rabbitTemplate.convertAndSend("test_exchange_confrim","test_confirm.haha","1321231231");
        //测试长度
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("test_exchange_confrim","test_confirm.haha","1321231231");
        }
    }
}
