package com.indi.micropro.mpuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MpUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpUserServiceApplication.class, args);
	}

}
