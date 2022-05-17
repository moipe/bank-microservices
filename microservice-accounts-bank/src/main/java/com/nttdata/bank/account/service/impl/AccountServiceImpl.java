package com.nttdata.bank.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.nttdata.bank.account.client.CustomerClientRest;
import com.nttdata.bank.account.client.ProductClientRest;
import com.nttdata.bank.account.client.TransactionClientRest;
import com.nttdata.bank.account.dto.AccountDTO;
import com.nttdata.bank.account.model.Account;
import com.nttdata.bank.account.repository.AccountRepository;
import com.nttdata.bank.account.service.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionClientRest transactionClientRest;
	
	@Autowired
	private ProductClientRest productClientRest;
	
	@Autowired
	private CustomerClientRest customerClientRest;
	
	@Autowired
	private JsonMapper jsonMapper;

	@Override
	public Flux<AccountDTO> findByCustomerId(String customerId) {
		
		Flux<AccountDTO> accountDTO = accountRepository.findAccountByCustomerId(customerId).map(a -> convertirAAccountDTO(a));
		
		return accountDTO.flatMap( account -> 
										Mono.just(account)
										.zipWith(transactionClientRest.findByAccountId(account.get_id())
													.collectList(),
													(a, t) -> {
														a.setTransactions(t);
														return a;
													}
												)
										.zipWith(productClientRest.findById(account.getProductId())
													,(a, p) -> {
														a.setProduct(p);
														return a;
													}
												)
								);
		
	}

	@Override
	public Mono<Account> save(Account account) {		
		return customerClientRest.showCustomerInformationById(account.getCustomerId())
						.flatMap( customer -> {
							Mono<Account> accountMono = Mono.empty();
							if(customer.getType().equals("Personal")) {
								accountMono = accountRepository.findAccountByCustomerId(account.getCustomerId())
									.any(a -> a.getProductId().equals(account.getProductId()))
									.flatMap(value ->
										(value) ? productClientRest.findById(account.getProductId())
													.filter(product -> product.getName().equals("Plazo fijo"))
													.switchIfEmpty(Mono.error(new Exception("Ya existe una cuenta con ese producto 11")))
													.flatMap(product -> accountRepository.save(account))
												: accountRepository.save(account));
							}
							if(customer.getType().equals("Empresarial")) {
								accountMono = productClientRest.findById(account.getProductId())
											.filter(product -> product.getName().equals("Cuenta corriente"))
											.switchIfEmpty(Mono.error(new Exception("Un cliente empresarial solo puede tener cuenta corriente.")))
											.flatMap(product -> accountRepository.save(account));
							}
					
					return accountMono;
				}
			 );
	}
	
	
	@Override
	public Mono<Account> updateBalance(String id, Double balance, String type) {
		return accountRepository.findById(id)
				.flatMap(a -> {
					Mono<Account> accountMono = Mono.empty();
					Double newBalance = 0D;
					if(type.equals("1")) {
						newBalance = a.getBalance() + balance;
						a.setBalance(newBalance);
						accountMono = accountRepository.save(a);
					}
					if(type.equals("2")) {
						
						/*if(a.getBalance() < balance) {
							accountMono = Mono.error(new Exception("No tiene saldo suficiente."));
						} else {
							newBalance = a.getBalance() - balance;
							a.setBalance(newBalance);
							accountMono = accountRepository.save(a);
						}*/
						if(a.getBalance() > balance) {
							newBalance = a.getBalance() - balance;
							a.setBalance(newBalance);
							accountMono = accountRepository.save(a);
						}
					}
					return accountMono;
				});
	}
	
	private AccountDTO convertirAAccountDTO(Account account) {
		return jsonMapper.convertValue(account, AccountDTO.class);
	}

}
