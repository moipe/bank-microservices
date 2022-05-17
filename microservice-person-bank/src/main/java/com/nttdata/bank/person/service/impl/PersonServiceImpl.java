package com.nttdata.bank.person.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bank.person.model.Person;
import com.nttdata.bank.person.repository.PersonRepository;
import com.nttdata.bank.person.service.PersonService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public Flux<Person> findByCustomerId(String customerId) {
		return personRepository.findPersonByCustomerId(customerId);
	}

	@Override
	public Mono<Person> save(Person person) {
		return personRepository.save(person);
	}

}
