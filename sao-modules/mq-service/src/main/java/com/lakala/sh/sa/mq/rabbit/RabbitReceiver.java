package com.lakala.sh.sa.mq.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者Receiver
 */
@Component
@RabbitListener(queues = "hello") // todo 用时打开
public class RabbitReceiver {

    private static int number = 1;
    @RabbitHandler
    public void process(String hello) {
        System.out.println(number++ +">>>Receiver : " + hello);
    }
}
