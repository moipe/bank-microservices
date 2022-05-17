package com.nttdata.bank.account.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	private String _id;
	
	private String type;
	
	//@JsonInclude(Include.NON_NULL)
	private String name;

	//@JsonInclude(Include.NON_NULL)
	private String document;

}
