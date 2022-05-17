package com.nttdata.bank.account.service;

import com.nttdata.bank.account.dto.AccountDTO;
import com.nttdata.bank.account.model.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
	
	Flux<AccountDTO> findByCustomerId(String customerId);
	
	Mono<Account> save(Account account);
	
	Mono<Account> updateBalance(String id, Double balance, String type);

}
