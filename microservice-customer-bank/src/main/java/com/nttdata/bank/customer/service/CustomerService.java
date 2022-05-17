package com.nttdata.bank.customer.service;

import com.nttdata.bank.customer.dto.CustomerDTO;
import com.nttdata.bank.customer.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
	
	Flux<CustomerDTO> findAll();
	
	Mono<CustomerDTO> findById(String id);
	
	Mono<Customer> save(Customer customer);
	
	Mono<Customer> showCustomerInformationById(String id);

}
