package com.hytrix.hytrixt.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class CommandWithFallbackViaNetwork extends HystrixCommand<String> {
    private final int id;

    protected CommandWithFallbackViaNetwork(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueCommand")));
        this.id = id;
    }

    @Override
    protected String run() {
        // RemoteService.getValue(id);
        System.out.println("CommandWithFallbackViaNetwork       "+Thread.currentThread().getName());
        throw new RuntimeException("force failure for example");
    }

    @Override
    protected String getFallback() {
        return new FallbackViaNetwork(id).execute();
    }


    private static class FallbackViaNetwork extends HystrixCommand<String> {
        private final int id;
        public FallbackViaNetwork(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueFallbackCommand"))
                    // 使用不同的线程池做隔离，防止上层线程池跑满，影响降级逻辑.
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RemoteServiceXFallback")));
            this.id = id;
        }
        @Override
        protected String run() {
            System.out.println("Fallback      "+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "run"+Thread.currentThread().getName();
        }

        @Override
        protected String getFallback() {
            return null;
        }
    }


    public static void main(String[] args) throws Exception{
        CommandWithFallbackViaNetwork command = new CommandWithFallbackViaNetwork(2);
        String result = command.execute();
        System.out.println(result);
        System.out.println("MainThread:" + Thread.currentThread().getName());
    }
}
