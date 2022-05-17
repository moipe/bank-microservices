package com.nttdata.bank.transaction.service;

import com.nttdata.bank.transaction.model.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
	
	Flux<Transaction> findByAccountId(String accountId);
	
	Mono<Transaction> findById(String id);
	
	Mono<Transaction> save(Transaction transaction);
	
	Mono<Transaction> update(Transaction transaction);
	
	//Mono<Account> actualizar(String id, Double balance);

}
