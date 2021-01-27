package com.eService.customers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eService.customers.Account;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;

@RestController
@EnableAutoConfiguration
@RibbonClient(name="custribbon")
@EnableCircuitBreaker 
public class AccountController {

	final static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/accountList")
	public List<Account> all() {
		logger.info("accounts-microservice all() invoked");
		List<Account> accounts = accountRepository.getAllAccounts();
		logger.warn("accounts-microservice all() found: " + accounts.size());
		logger.error("accounts-microservice all() no error: " + accounts.size());
		return accounts;
	}
	
	
	@HystrixCommand(fallbackMethod="accountDetailsFallback")
	@RequestMapping("/accountDetails/{id}")
	public Account accountDetails(@PathVariable("id") String id) {
		if(id == "0000") {
		   throw new RuntimeException();
		}
		Account account = accountRepository.getAccount(id);
		return account;
	}
	
	public Account accountDetailsFallback(String id) {
		logger.info("Error212 occured:-Rakesh Jha");
		System.out.println("Error121 occured:-Rakesh Jha");
		return new Account(0000l, "Please try after some time" , "0000");
	}
	
	
}
