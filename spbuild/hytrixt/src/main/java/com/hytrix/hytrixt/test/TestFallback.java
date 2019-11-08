package com.hytrix.hytrixt.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;
//重载HystrixCommand 的getFallback方法实现逻辑
public class TestFallback extends HystrixCommand<String> {

    private final String name;
    public TestFallback(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                /* 配置依赖超时时间,500毫秒*/
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)));
        this.name = name;
    }
    @Override
    protected String getFallback() {
        System.out.println("exeucute Falled");
        return "exeucute Falled";
    }
    @Override
    protected String run() throws Exception {
        //sleep 1 秒,调用会超时
        String s="Hello " + name +" thread:" + Thread.currentThread().getName();
        System.out.println(s);
        TimeUnit.MILLISECONDS.sleep(1000);

        System.out.println(s);
        return s;
    }
    public static void main(String[] args) throws Exception{
        TestFallback command = new TestFallback("test-Fallback");
        String result = command.execute();
    }
}
