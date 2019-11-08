package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@EnableEurekaClient
@SpringBootApplication
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class Sp2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sp2Application.class, args);
	}
	@Value("${server.port}")
	String port;

	int i=0;

	int j=0;

	@RequestMapping("/hi")
	public String home(@RequestParam String name)
	{
		i++;

		System.out.println("*****************hi*************************"+i);

		return "hi " + name + ",i am from port:" + port+"and this is "+i+"request";
	}

	@RequestMapping("/hi2/test")
	public Object home1(@RequestParam String name)
	{
		j++;

		System.out.println("*****************hi2222222*******************"+j);

		Map<String,Object> map=new HashMap<>();
		map.put("name",name);
		map.put("str","hi");
		return "test"+j;
	}
}
