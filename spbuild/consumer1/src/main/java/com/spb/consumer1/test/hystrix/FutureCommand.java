package com.spb.consumer1.test.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class FutureCommand {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloService() throws ExecutionException, InterruptedException {

        Future<String> future = new AsyncResult<String>() {
            @Override
            public String invoke() {
                String result=restTemplate.getForObject("http://HI/hi?name=" , String.class).toString();
                System.out.println(result);
                return result;
            }
        };
        return future.get();
    }


public String helloFallBack(){
return "error";
}
}
