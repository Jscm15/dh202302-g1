package com.dh.margin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiMarginsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMarginsApplication.class, args);
	}

}
