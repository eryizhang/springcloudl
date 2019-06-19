package com.example.demo.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "HI")
public interface HelloWorldService {
    @RequestMapping(value = "/hi?name=123",method = RequestMethod.GET)
    String sayHello();
}
