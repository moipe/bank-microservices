package com.nttdata.bank.person.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bank.person.model.Person;

import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String>{
	
	Flux<Person> findPersonByCustomerId(String customerId);

}
