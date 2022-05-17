package com.nttdata.bank.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bank.account.dto.AccountDTO;
import com.nttdata.bank.account.model.Account;
import com.nttdata.bank.account.service.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	private Flux<AccountDTO> findByCustomerId(@RequestParam String customerId){
		
		return accountService.findByCustomerId(customerId);
		
	}
	
	@PostMapping
	private Mono<Account> save(@RequestBody Account account, 
								@RequestParam String customerId, 
								@RequestParam String productId){
		
		account.setCustomerId(customerId);
		
		account.setProductId(productId);
		
		return accountService.save(account);
		
	}
	
	@PutMapping("/{id}")
	private Mono<Account> updateBalance(@PathVariable String id, 
										@RequestParam Double balance,
										@RequestParam String type) {
		
		return accountService.updateBalance(id, balance, type);
	}
}
