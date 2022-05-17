package com.nttdata.bank.account.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.bank.account.model.Transaction;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name = "service-transaction", url = "localhost:9960")
public interface TransactionClientRest {
	
	//@GetMapping("/transaction")
	@GetMapping
	public Flux<Transaction> findByAccountId(@RequestParam String accountId);

}