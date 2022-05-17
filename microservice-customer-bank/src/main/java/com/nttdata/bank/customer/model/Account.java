package com.nttdata.bank.customer.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	private String _id;
	
	private Double balance;
	
	@JsonIgnore
	private String customerId;
	
	@JsonIgnore
	private String productId;
	
	private Product product;
	
	private List<Transaction> transactions = new ArrayList<Transaction>();

}