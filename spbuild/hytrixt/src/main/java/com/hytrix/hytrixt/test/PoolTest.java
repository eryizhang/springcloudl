package com.hytrix.hytrixt.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class PoolTest  extends HystrixCommand<String> {
    private final String name;
    public PoolTest(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld"))
                /* 使用HystrixThreadPoolKey工厂定义线程池名称*/
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorldPool")));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "HystrixThread:" + Thread.currentThread().getName();
    }
    public static void main(String[] args) throws Exception{
        PoolTest command = new PoolTest("pool");
        String result = command.execute();
        System.out.println(result);
        System.out.println("MainThread:" + Thread.currentThread().getName());
    }
}
