package com.hytrix.hytrixt.test;

import com.netflix.hystrix.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HystrixCircuitBreakerTest extends HystrixCommand<String> {

    private final String name;

    private MorningService morningService;

    public HystrixCircuitBreakerTest(String name, MorningService morningService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircuitBreakerTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakerTest"))
                .andThreadPoolPropertiesDefaults(   // 配置线程池
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(200)  // 配置线程池里的线程数，设置足够多线程，以防未熔断却打满threadpool
                )
                .andCommandPropertiesDefaults(  // 配置熔断器
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerEnabled(true)  //  熔断器在整个统计时间内是否开启的阀值
                                .withCircuitBreakerRequestVolumeThreshold(3)    // 至少有3个请求才进行熔断错误比率计算
                                .withCircuitBreakerErrorThresholdPercentage(50)   //当出错率超过50%后熔断器启动
                                .withMetricsRollingStatisticalWindowInMilliseconds(5000)   // 统计滚动的时间窗口
                                .withCircuitBreakerSleepWindowInMilliseconds(2000)   // 熔断器工作时间，超过这个时间，先放一个请求进去，成功的话就关闭熔断，失败就再等一段时间
                )
        );
        this.name = name;
        this.morningService = morningService;
    }

    @Override
    protected String run() throws Exception {
        int num = Integer.valueOf(name);
        if(num % 2 == 0) {  // 直接返回
            return name;
        } else {
            morningService.timeout(); // 无限循环模拟超时
        }
        return name;
    }


    /**
     * Whether or not your command has a fallback,
     * all of the usual Hystrix state and circuit-breaker state/metrics are updated to indicate the command failure.
     */
    @Override
    protected String getFallback() {
        return "CircuitBreaker fallback: " + name;
    }

    public static class UnitTest {

        @Test
        public void testSynchronous() throws IOException {
            for(int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("===========" + new HystrixCircuitBreakerTest(String.valueOf(i), new MorningService()).execute());
                    // 查看熔断器是否打开
                    System.out.println("if circuit breaker open: " + HystrixCircuitBreaker.Factory.getInstance(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey")).isOpen());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
