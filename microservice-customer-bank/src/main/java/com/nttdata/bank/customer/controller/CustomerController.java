package com.nttdata.bank.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bank.customer.dto.CustomerDTO;
import com.nttdata.bank.customer.model.Customer;
import com.nttdata.bank.customer.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	private Flux<CustomerDTO> findAll(){
		
		return customerService.findAll();
		
	}
	
	@GetMapping("/{id}")
	private Mono<CustomerDTO> findByid(@PathVariable String id){
		
		return customerService.findById(id);
		
	}
	
	@PostMapping
	private Mono<Customer> save(@RequestBody Customer customer){
		
		return customerService.save(customer);
		
	}

	
	@GetMapping("/customer/{id}")
	private Mono<Customer> showCustomerInformationById(@PathVariable String id){
		
		return customerService.showCustomerInformationById(id);
		
	}
	
}
