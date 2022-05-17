package com.nttdata.bank.customer.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.bank.customer.model.Person;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name = "service-person", url = "localhost:9958")
public interface PersonClientRest {
	
	//@GetMapping("/person")
	@GetMapping
	public Flux<Person> findByCustomerId(@RequestParam String customerId);

}