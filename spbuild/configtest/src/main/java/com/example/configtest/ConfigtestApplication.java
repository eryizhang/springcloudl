package com.example.configtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class ConfigtestApplication {
	@Value("${test}")
	String port;
	public static void main(String[] args) {

		SpringApplication.run(ConfigtestApplication.class, args);
	}

}
