package com.spb.consumer1.test.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReceiverMsg {
    //此处注解指定去获取名为myQueue的queues队列消息

    @RabbitListener(queues = "myQueue")
    public void printMQ(String message){
        System.out.println("【队列消息】ReceiverMsg ,printMQ={}+"+message);
    }

   @RabbitListener(bindings = @QueueBinding(
            key = "milkTea",
            value = @Queue("milkTeaOrder"),
            exchange = @Exchange("myOrder")
    ))
    @RabbitListener(queues = "myOrder")
    public void milkTeaMQ(String message){
        System.out.println("【队列消息】ReceiverMsg.milkTeaMQ ,milkTea={}"+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            key = "mac",
            value = @Queue("macOrder"),
            exchange = @Exchange("macOrder")
    ))
    public void macMQ(String message){
        System.out.println("【队列消息】ReceiverMsg.macMQ ,macMQ={}"+message);
    }


}
