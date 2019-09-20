package com.feignt.feigntest.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-ribbon1")
@Component
public interface   ConsumerService2  {

    @RequestMapping(value = "/hiSimple?name=fys321",method = RequestMethod.GET)
    String sayHello2();
}
