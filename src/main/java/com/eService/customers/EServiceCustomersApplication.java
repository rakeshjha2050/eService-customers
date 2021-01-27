package com.eService.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class EServiceCustomersApplication {
	public static final String ACCOUNTS_SERVICE_URL = "http://localhost:1111";

	public static void main(String[] args) {
		SpringApplication.run(EServiceCustomersApplication.class, args);
	}
	
	@Bean
	public AccountRepository accountRepository() {
		return new RemoteAccountRepository(ACCOUNTS_SERVICE_URL);
	}

}
