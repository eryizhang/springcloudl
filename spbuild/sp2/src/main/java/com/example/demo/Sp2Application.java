package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class Sp2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sp2Application.class, args);
	}
	@Value("${server.port}")
	String port;

	@RequestMapping("/hi")
	public String home(@RequestParam String name)
	{
		System.out.println("*****************hi*************************");
		return "hi " + name + ",i am from port:" + port;
	}

	@RequestMapping("/hi2")
	public Object home1(@RequestParam String name)
	{
		System.out.println("*****************hi2222222*******************");
		Map<String,Object> map=new HashMap<>();
		map.put("name",name);
		map.put("str","hi");
		return map;
	}
}
