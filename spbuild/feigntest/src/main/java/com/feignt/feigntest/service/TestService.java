package com.feignt.feigntest.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TestService {

    int i=0;
    @Autowired
    RestTemplate restTemplate;

    // 断路器配置，当无法调用如下方法时，就会调用自定的hiError方法。
    @HystrixCommand(commandKey = "testCommand", groupKey = "testGroup", threadPoolKey = "testThreadKey",
            fallbackMethod = "hiError", ignoreExceptions = {NullPointerException.class},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            }
    )
    public String testHytrix(String name)
    {
        System.out.println("testHytrix");
        //ResponseEntity<Object> forEntity = restTemplate.getForEntity("http://SERVICE-HI/hi?name=" + name, Object.class);
        String forObject = restTemplate.getForObject("http://HI/hi?name=" + name, String.class);
        return forObject.toString();
    }



    public String hiError(String name)
    {
        i++;
        System.out.println("hiError"+i);
        return "hey " +
                name + ", there is some problem with hi page";
    }
}
