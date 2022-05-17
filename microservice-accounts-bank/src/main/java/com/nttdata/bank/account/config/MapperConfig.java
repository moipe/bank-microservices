package com.nttdata.bank.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class MapperConfig {
	
	@Bean
	public JsonMapper getJsonMapper() {
		JsonMapper jsonMapper = new JsonMapper();
		jsonMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return new JsonMapper();
	}

}
