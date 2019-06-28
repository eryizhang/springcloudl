package com.example.configtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RefreshScope
public class ConfigtestApplication {
	@Value("${test}")
	String port;
	public static void main(String[] args) {

		SpringApplication.run(ConfigtestApplication.class, args);
	}

	@RequestMapping("/hi")
	public String home(@RequestParam String name)
	{
		System.out.println("*****************hi*************************");
		return "hi " + name + ",i am from port:" + port;
	}

	@StreamListener(StreamClient.MSG)
	@SendTo("otherStream")
	public void stream(Object msg){
		System.out.println("【SpringCloud Stream】StreamListen.stream={}"+msg);
	}

}
