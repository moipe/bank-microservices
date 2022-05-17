package com.nttdata.bank.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.nttdata.bank.customer.client.AccountClientRest;
import com.nttdata.bank.customer.client.PersonClientRest;
import com.nttdata.bank.customer.dto.CustomerDTO;
import com.nttdata.bank.customer.model.Customer;
import com.nttdata.bank.customer.repository.CustomerRepository;
import com.nttdata.bank.customer.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PersonClientRest personClientRest;
	
	@Autowired
	private AccountClientRest accountClientRest;
	
	@Autowired
	private JsonMapper jsonMapper;
	
	

	@Override
	public Flux<CustomerDTO> findAll() {
		
		Flux<CustomerDTO> customerDTO = customerRepository.findAll().map(c ->convertirACustomerDTO(c));
		
		return customerDTO.flatMap(customer ->
										Mono.just(customer)
										.zipWith(personClientRest.findByCustomerId(customer.get_id())
												 .collectList(),
												 (c, p) -> {
													 c.setPersons(p);
													 return c;
												 }
												)
										 .zipWith(accountClientRest.findByCustomerId(customer.get_id())
												 .collectList(),
												 (c, a) -> {
													 c.setAccount(a);
													 return c;
												  }
												 )
							);
	}
	
	@Override
	public Mono<CustomerDTO> findById(String id) {
		
		Mono<CustomerDTO> customerDTO = customerRepository.findById(id).map(c ->convertirACustomerDTO(c));
		
		return customerDTO.flatMap(customer ->
										Mono.just(customer)
										.zipWith(personClientRest.findByCustomerId(customer.get_id())
												 .collectList(),
												 (c, p) -> {
													 c.setPersons(p);
													 return c;
												 }
												)
										.zipWith(accountClientRest.findByCustomerId(customer.get_id())
												 .collectList(),
												 (c, a) -> {
													 c.setAccount(a);
													 return c;
												  }
												 )
							);
	}
	
	@Override
	public Mono<Customer> save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	
	private CustomerDTO convertirACustomerDTO(Customer customer) {
		return jsonMapper.convertValue(customer, CustomerDTO.class);
	}

	@Override
	public Mono<Customer> showCustomerInformationById(String id) {
		return customerRepository.findById(id);
	}

}
