package com.nttdata.bank.transaction.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "transactions")
public class Transaction {
	
	@Id
	private String _id;
	
	private String type;
	
	private Double amount;
	
	//@JsonIgnore
	@JsonProperty(access = Access.WRITE_ONLY)
	private String accountId;

}
