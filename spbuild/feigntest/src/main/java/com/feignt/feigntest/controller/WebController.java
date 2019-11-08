package com.feignt.feigntest.controller;


import com.feignt.feigntest.service.ConsumerService2;
import com.feignt.feigntest.service.HelloWorldService;
import com.feignt.feigntest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {
    @Autowired
    HelloWorldService helloWorldFeignService;

    @Autowired
    ConsumerService2 consumerService;

    @Autowired
    TestService testService;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        System.out.println("***************hello*********");
        String s="123";
        return helloWorldFeignService.sayHello();
    }

    @RequestMapping(value = "/hello2/t",method = RequestMethod.GET)
    public String sayHello1(){
        //System.out.println("***************hello2222*********");
        return consumerService.sayHello2();
    }

    @RequestMapping(value = "/hello9/t",method = RequestMethod.GET)
    public String sayHello9(){
        //System.out.println("***************hello2222*********");
        return testService.testHytrix("test");
    }


}
