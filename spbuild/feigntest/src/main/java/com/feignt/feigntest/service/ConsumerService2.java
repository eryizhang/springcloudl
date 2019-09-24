package com.feignt.feigntest.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-ribbon1",fallback=FallbackConsumer.class)
public interface   ConsumerService2  {

    @RequestMapping(value = "/hiSimple?name=ConsumerService2",method = RequestMethod.GET)
    String sayHello2();
}
