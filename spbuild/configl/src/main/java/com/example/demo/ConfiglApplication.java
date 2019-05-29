package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEurekaServer
@EnableConfigServer
@SpringBootApplication
public class ConfiglApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfiglApplication.class, args);
	}

}
