package com.nttdata.bank.transaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bank.transaction.model.Transaction;

import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String>{
	
	Flux<Transaction> findTransactionByAccountId(String accountId);

}
