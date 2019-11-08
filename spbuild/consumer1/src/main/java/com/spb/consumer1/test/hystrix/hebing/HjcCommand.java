package com.spb.consumer1.test.hystrix.hebing;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cong on 2018/5/13.
 */
public class HjcCommand extends HystrixCommand<List<String>> {

    private RestTemplate restTemplate;
    private List<Long> ids;


    public HjcCommand(String commandGroupKey, RestTemplate restTemplate,List<Long> ids) {
        //根据commandGroupKey进行线程隔离
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
        this.ids = ids;
    }

    @Override
    protected List<String> run() throws Exception {
        System.out.println("发送请求。。。参数为："+ids.toString()+Thread.currentThread().getName());
        String result = restTemplate.getForObject("http://HI/hi?name=first"+ids , String.class).toString();
        String []results=new String[2];
        String s1=restTemplate.getForObject("http://HI/hi?name=second"+ids , String.class).toString();
        results[0]=result;
        results[1]=s1;
        return Arrays.asList(results);
    }
}
