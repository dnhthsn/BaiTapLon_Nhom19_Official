package com.baitaplon.benhnhanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BenhnhanserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenhnhanserviceApplication.class, args);
	}

}
