package com.baitaplon.lichkhamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LichkhamserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LichkhamserviceApplication.class, args);
	}

}
