package com.nttdata.bank.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	
	private String _id;
	
	private String type;
	
	private Double amount;
	
	@JsonIgnore
	private String accountId;

}
