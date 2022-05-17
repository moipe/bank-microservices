package com.nttdata.bank.person.service;

import com.nttdata.bank.person.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
	
	Flux<Person> findByCustomerId(String customerId);
	
	Mono<Person> save(Person person);

}
