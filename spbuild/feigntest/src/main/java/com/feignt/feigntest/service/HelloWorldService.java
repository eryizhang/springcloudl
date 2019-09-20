package com.feignt.feigntest.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "HI",fallback=FallbackService.class)
public interface HelloWorldService {
    @RequestMapping(value = "/hi?name=123",method = RequestMethod.GET)
    String sayHello();

    @RequestMapping(value = "/hiSimple?name=fys321",method = RequestMethod.GET)
    String sayHello2();


}
