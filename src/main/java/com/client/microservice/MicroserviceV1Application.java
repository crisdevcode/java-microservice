package com.client.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.client.microservice"})
@SpringBootApplication
public class MicroserviceV1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceV1Application.class, args);
	}

}
