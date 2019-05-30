package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ConfigclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigclientApplication.class, args);
	}
	@Value("${test}") // git配置文件里的key
			String test;

	@RequestMapping(value = "/hi")
	public String hi(){
		return test;
	}
}
