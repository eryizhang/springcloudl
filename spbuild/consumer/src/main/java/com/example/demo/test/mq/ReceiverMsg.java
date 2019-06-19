package com.example.demo.test.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReceiverMsg {
    //此处注解指定去获取名为myQueue的queues队列消息

    @RabbitListener(queues = "myQueue")
    public void printMQ(String message){
        log.info("【队列消息】ReceiverMsg ,printMQ={}",message);
    }


}
