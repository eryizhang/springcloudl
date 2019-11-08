package com.hytrix.hytrixt.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;



public class RequestCacheCommand extends HystrixCommand<String> {
    private final int id;
    public RequestCacheCommand( int id) {
        super(HystrixCommandGroupKey.Factory.asKey("RequestCacheCommand"));
        this.id = id;
    }
    @Override
    protected String run() throws Exception {
        System.out.println("run      "+Thread.currentThread().getName() + " execute id=" + id);
        return "executed=" + id;
    }
    //重写getCacheKey方法,实现区分不同请求的逻辑
    @Override
    protected String getCacheKey() {
        //System.out.println("getCacheKey      "+Thread.currentThread().getName() + " execute id=" + id);
        return String.valueOf(id);
    }

    public static void main(String[] args){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command2a = new RequestCacheCommand(2);
            RequestCacheCommand command2b = new RequestCacheCommand(2);
            System.out.println("assert "+command2a.execute());
            //isResponseFromCache判定是否是在缓存中获取结果
            System.out.println("assert "+command2a.isResponseFromCache());
            System.out.println("assert "+command2b.execute());
            System.out.println("assert "+command2b.isResponseFromCache());
        } finally {
            //context.shutdown();
        }
       // context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command3b = new RequestCacheCommand(2);
            System.out.println("assert "+command3b.execute());
            System.out.println("assert "+command3b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
    }
}
