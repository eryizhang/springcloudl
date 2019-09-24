package com.feignt.feigntest.service;

import org.springframework.stereotype.Component;

@Component
public class FallbackConsumer implements ConsumerService2 {
    @Override
    public String sayHello2() {
        System.out.println("ConsumerService2 is not available !");
        return "ConsumerService2 is not available !";
    }
}
