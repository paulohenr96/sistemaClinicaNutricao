package com.nutricao.aplicacaonutricao.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class JsonMapper {

	
	public static String mapearJson(Object collect) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String writeValueAsString = "";
		try {
			writeValueAsString=	objectMapper.writeValueAsString(collect);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return writeValueAsString;
	}
}
