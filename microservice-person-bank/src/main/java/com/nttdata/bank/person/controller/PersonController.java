package com.nttdata.bank.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bank.person.model.Person;
import com.nttdata.bank.person.service.PersonService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	private Flux<Person> findByCustomerId(@RequestParam String customerId){
		
		return personService.findByCustomerId(customerId);
		
	}
	
	@PostMapping
	private Mono<Person> save(@RequestBody Person person, @RequestParam String customerId){
		
		person.setCustomerId(customerId);
		
		return personService.save(person);
		
	}

}
