package com.nttdata.bank.customer.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.bank.customer.model.Account;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name = "service-account", url = "localhost:9959")
public interface AccountClientRest {
	
	//@GetMapping("/account")
	@GetMapping
	public Flux<Account> findByCustomerId(@RequestParam String customerId);

}
