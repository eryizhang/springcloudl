package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-ribbon1")
public interface   ConsumerService  {

    @RequestMapping(value = "/hiSimple?name=fys321",method = RequestMethod.GET)
    String sayHello2();
}
