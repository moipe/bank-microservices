package com.nttdata.bank.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	private String _id;
	
	private String name;
	
	private String surnames;
	
	private int mobile;
	
	private String email;
	
	private String document;
	
	private String type;
	
	@JsonIgnore
	private String customerId;

}
