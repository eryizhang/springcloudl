package com.spb.streamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@EnableEurekaClient
@EnableCircuitBreaker
@EnableDiscoveryClient*/
public class StreampApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreampApplication.class, args);
	}

}
