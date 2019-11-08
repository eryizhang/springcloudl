package com.spb.consumer1.test.hystrix.geli;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

public class Keyg extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    protected Keyg(String commandGroupKey,RestTemplate restTemplate) {
        //根据commandGroupKey进行线程隔离的
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return restTemplate.getForObject("http://HI/hi?name=" , String.class).toString();
    }


    @Override
    protected String getFallback() {
        return "error";
    }


    //Hystrix的缓存
    @Override
    protected String getCacheKey() {
        //一般动态的取缓存Key,比如userId，这里为了做实验写死了，写为hello
        return "hello";
    }
}
