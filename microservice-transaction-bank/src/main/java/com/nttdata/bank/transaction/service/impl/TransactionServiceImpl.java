package com.nttdata.bank.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bank.transaction.client.AccountClientRest;
import com.nttdata.bank.transaction.model.Transaction;
import com.nttdata.bank.transaction.repository.TransactionRepository;
import com.nttdata.bank.transaction.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired 
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountClientRest accountClientRest;

	@Override
	public Flux<Transaction> findByAccountId(String accountId) {
		return transactionRepository.findTransactionByAccountId(accountId);
	}

	@Override
	public Mono<Transaction> findById(String id) {
		return transactionRepository.findById(id);
	}

	@Override
	public Mono<Transaction> save(Transaction transaction) {
		/*return transactionRepository.save(transaction)
					.flatMap(t -> {
							Mono<Transaction> TransactionMono = Mono.empty();
							if(t.getType().equals("Depósito")) {
								accountClientRest.updateBalance(t.getAccountId(), t.getAmount(), "1").block();
								TransactionMono = Mono.just(t);
							}
							if(t.getType().equals("Retiro")) {
								
								Account account = accountClientRest.updateBalance(t.getAccountId(), t.getAmount(), "2").block();
								if(account != null) TransactionMono = Mono.just(t);
							}

							return TransactionMono;
						});*/
		
		Mono<Transaction> oTransaction = Mono.just(transaction);
		return oTransaction.flatMap(t -> {
						Mono<Transaction> TransactionMono = Mono.empty();
						if(t.getType().equals("Depósito")) {
							TransactionMono = accountClientRest.updateBalance(t.getAccountId(), t.getAmount(), "1")
													.flatMap(tr -> transactionRepository.save(transaction));
						}
						if(t.getType().equals("Retiro")) {
							TransactionMono = accountClientRest.updateBalance(t.getAccountId(), t.getAmount(), "2")
													.switchIfEmpty(Mono.error(new Exception("No tiene saldo suficiente.")))
													.flatMap(tr -> transactionRepository.save(transaction));
						}

						return TransactionMono;
		});
	}

	@Override
	public Mono<Transaction> update(Transaction transaction) {
		
		Mono<Transaction> oTransaction = transactionRepository.findById(transaction.get_id());
		
		return oTransaction.flatMap( transactions -> {
										transactions.setType(transaction.getType());
										transactions.setAmount(transaction.getAmount());
										return transactionRepository.save(transactions);
									 }
			
							);
	}

	/*@Override
	public Mono<Account> actualizar(String id, Double balance) {
		return accountClientRest.updateBalance(id, balance);
	}*/

}
