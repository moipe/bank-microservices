package com.nttdata.bank.account.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.bank.account.model.Customer;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "service-customer", url = "localhost:9957")
public interface CustomerClientRest {
	
	@GetMapping("/customer/{id}")
	public Mono<Customer> showCustomerInformationById(@PathVariable String id);

}
