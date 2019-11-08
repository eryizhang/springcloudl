package com.spb.consumer1.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

public class HelloServiceCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    protected HelloServiceCommand(HystrixCommandGroupKey group) {

        super(group);

    }

    protected HelloServiceCommand(String commandGroupKey,RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
    }
    //服务调用
    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName());

        String result=restTemplate.getForObject("http://HI/hi?name=" , String.class).toString();
        System.out.println(result);
        return result;
    }
    //服务降级时所调用的Fallback()
    @Override
    protected String getFallback() {
        return "error";
    }
}
