package com.nttdata.bank.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private String _id;
	
	private String name;
	
	private String type;
	
	private String category;

}
