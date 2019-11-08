package com.spb.consumer1.test;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class HelloService
{
int  i=0;
int j=0;
    @Autowired
    RestTemplate restTemplate;

    // 断路器配置，当无法调用如下方法时，就会调用自定的hiError方法。
   // @HystrixCommand(fallbackMethod = "hiError")
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
    public String hiService(String name)
    {
        //ResponseEntity<Object> forEntity = restTemplate.getForEntity("http://SERVICE-HI/hi?name=" + name, Object.class);
        System.out.println("发送命令");
        String forObject = restTemplate.getForObject("http://HI/hi?name=" + name, String.class);
        System.out.println("返回成功");
        return forObject.toString();
    }

    // 断路器配置，当无法调用如下方法时，就会调用自定的hiError方法。
    @HystrixCommand(fallbackMethod = "hiError1")
  //  @CacheResult(cacheKeyMethod = "hiServiceFIGN")
    public String hiServiceFIGN(String name)
    {
        System.out.println("hiServiceFIGN发送命令");
        //ResponseEntity<Object> forEntity = restTemplate.getForEntity("http://SERVICE-HI/hi?name=" + name, Object.class);
        String forObject = restTemplate.getForObject("http://FIGN/hello?name=" + name, String.class);
        return forObject.toString();
    }

    public String hiError(String name)
    {
        i++;
        System.out.println("发生异常走其他"+i+"       "+new Date(System.currentTimeMillis()));
        return "hey " +
                name + ", there is some problem with hi page";
    }

    public String hiError1(String name)
    {
        j++;
        System.out.println("hiError1   "+j+"       "+new Date(System.currentTimeMillis()));
        return "hey " +
                name + ", there is some problem with hi page";
    }
}
