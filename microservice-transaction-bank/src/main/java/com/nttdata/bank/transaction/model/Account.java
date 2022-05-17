package com.nttdata.bank.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	private String _id;
	
	private Double balance;
	
	private String customerId;
	
	private String productId;

}
