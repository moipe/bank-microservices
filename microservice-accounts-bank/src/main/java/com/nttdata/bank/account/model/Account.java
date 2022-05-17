package com.nttdata.bank.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "accounts")
public class Account {
	
	@Id
	private String _id;
	
	private Double balance;
	
	//@JsonIgnore
	private String customerId;
	
	private String productId;


}
