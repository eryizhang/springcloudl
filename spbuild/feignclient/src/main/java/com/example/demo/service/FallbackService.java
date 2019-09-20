package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class FallbackService implements HelloWorldService {
    @Override
    public String sayHello() {
        System.out.println("hello world service is not available !");
        return "hello world service is not available !";
    }

    @Override
    public String sayHello2() {
        System.out.println("hello world service is not available !");
        return "hello world service is not available !";
    }
}
