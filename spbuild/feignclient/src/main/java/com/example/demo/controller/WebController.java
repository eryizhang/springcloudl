package com.example.demo.controller;

import com.example.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    HelloWorldService helloWorldFeignService;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        System.out.println("***************hello*********");
        return helloWorldFeignService.sayHello();
    }

    @RequestMapping(value = "/hello2/t",method = RequestMethod.GET)
    public String sayHello1(){
        System.out.println("***************hello2222*********");
        return helloWorldFeignService.sayHello();
    }
}
