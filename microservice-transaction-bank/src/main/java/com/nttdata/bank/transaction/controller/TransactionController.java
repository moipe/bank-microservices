package com.nttdata.bank.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bank.transaction.model.Transaction;
import com.nttdata.bank.transaction.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping
	private Flux<Transaction> findByAccountId(@RequestParam String accountId){
		
		return transactionService.findByAccountId(accountId);
		
	}
	
	@GetMapping("/{id}")
	private Mono<Transaction> findById(@PathVariable String id){
		
		return transactionService.findById(id);
		
	}
	
	@PostMapping
	private Mono<Transaction> save(@RequestBody Transaction transaction, @RequestParam String accountId){
		
		transaction.setAccountId(accountId);
		
		return transactionService.save(transaction);
		
	}
	
	@PutMapping("/{id}")
	private Mono<Transaction> update(@PathVariable String id, @RequestBody Transaction transaction){
		
		transaction.set_id(id);
		
		return transactionService.update(transaction);
		
	}
	
	/*@PutMapping("/account/{id}")
	private Mono<Account> actualizar(@PathVariable String id, @RequestParam Double balance){
		
		return transactionService.actualizar(id, balance);
		
	}*/

}
