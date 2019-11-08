package com.spb.consumer1.test.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.spb.consumer1.test.HelloService;
import com.spb.consumer1.test.hystrix.hebing.HjcBatchCommand;
import com.spb.consumer1.test.hystrix.observe.HelloServiceObserveCommand;
import com.spb.consumer1.test.hystrix.observe.HelloServiceObserveCommand1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by cong on 2018/5/8.
 */
@RestController
public class ConsumerController {


    @Autowired
    private HelloService helloService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HelloServiceObserveCommand1 helloServiceObserveCommand1;

    @Autowired
    FutureCommand futureCommand;

    @RequestMapping("/consumer")
    public String helloConsumer() throws ExecutionException, InterruptedException {

        HelloServiceCommand command = new HelloServiceCommand("hello",restTemplate);
        String result = command.execute();
        return result;
    }

    @RequestMapping("/future")
    public String future() throws ExecutionException, InterruptedException {

        String result = futureCommand.helloService();
        return result;
    }

    @RequestMapping("/observe")
    public String observe() throws ExecutionException, InterruptedException {

        List<String> list = new ArrayList<>();
        HelloServiceObserveCommand command = new HelloServiceObserveCommand("hello",restTemplate);
        //热执行
        //Observable<String> observable = command.observe();
        //冷执行
         Observable<String> observable =command.toObservable();
        // 订阅
        observable.subscribe(new Observer<String>() {
            //请求完成的方法
            @Override
            public void onCompleted() {
                System.out.println("会聚完了所有查询请求");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
            //订阅调用事件，结果会聚的地方，用集合去装返回的结果会聚起来。
            @Override
            public void onNext(String s) {
                System.out.println("结果来了.....");
                list.add(s);
            }
        });

        return list.toString();

    }


    @RequestMapping("/observe1")
    public String observe1() throws ExecutionException, InterruptedException {

        List<String> list = new ArrayList<>();
       // HelloServiceObserveCommand command = new HelloServiceObserveCommand("hello",restTemplate);
        //热执行
        //Observable<String> observable = command.observe();
        //冷执行
        //Observable<String> observable =command.toObservable();
       // Observable.create();

        helloServiceObserveCommand1.helloService();
        // 订阅
       /* observable.subscribe(new Observer<String>() {
            //请求完成的方法
            @Override
            public void onCompleted() {
                System.out.println("会聚完了所有查询请求");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
            //订阅调用事件，结果会聚的地方，用集合去装返回的结果会聚起来。
            @Override
            public void onNext(String s) {
                System.out.println("结果来了.....");
                list.add(s);
            }
        });*/

        return list.toString();

    }


    @RequestMapping("/keyg")
    public String keyg() throws ExecutionException, InterruptedException {

        //Hystrix的缓存实现，这功能有点鸡肋。
        HystrixRequestContext.initializeContext();
        HelloServiceCommand command = new HelloServiceCommand("hello",restTemplate);
        String execute = command.execute();//清理缓存
//       HystrixRequestCache.getInstance("hello").clear();
        HelloServiceCommand command1= new HelloServiceCommand("hello",restTemplate);
        String execute1 = command.execute();//清理缓存
        return null;




    }


    @RequestMapping("/hebing")
    public String hebing() throws ExecutionException, InterruptedException {


        //请求合并
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        HjcBatchCommand command = new HjcBatchCommand(restTemplate,1L);
        HjcBatchCommand command1 = new HjcBatchCommand(restTemplate,2L);
        HjcBatchCommand command2 = new HjcBatchCommand(restTemplate,3L);

        //这里你必须要异步，因为同步是一个请求完成后，另外的请求才能继续执行，所以必须要异步才能请求合并
        Future<String> future = command.queue();
        Future<String> future1 = command1.queue();

        String r = future.get();
        String r1 = future1.get();

        Thread.sleep(2000);
        //可以看到前面两条命令会合并，最后一条会单独，因为睡了2000毫秒，而你请求设置要求在200毫秒内才合并的。
        System.out.println("睡了两秒以后的清秋");
        Future<String> future2 = command2.queue();
        String r2 = future2.get();

        System.out.println(r);
        System.out.println(r1);
        System.out.println(r2);

        context.close();

        return null;

    }


}
