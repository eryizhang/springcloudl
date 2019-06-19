package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//由于之前对项目进行了项目多模块化,运行单元测试的时候需要引导main启动类
//没有可以不考虑classes = ServerApplication.class
@SpringBootTest(classes = ConsumerApplication.class)
@RunWith(SpringRunner.class)
public class MQTest extends ConsumerApplicationTests{

    //AmqpTemplate  操作MQ的API
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("myQueue","hello , MQ");
    }

}
