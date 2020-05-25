package com.indi.micropro.mpuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.indi.micropro.mpuserservice.shared.FeignErrorDecoder;

import feign.Logger;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class MpUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpUserServiceApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
	
	@Bean
	public FeignErrorDecoder getFeighErrorDecoder() {
		return new FeignErrorDecoder();
	}

}
