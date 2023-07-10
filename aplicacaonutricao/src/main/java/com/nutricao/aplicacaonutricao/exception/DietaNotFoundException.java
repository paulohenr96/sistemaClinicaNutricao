package com.nutricao.aplicacaonutricao.exception;

public class DietaNotFoundException extends RuntimeException{

	public DietaNotFoundException(Long id) {

		super(String.format("A dieta com id %d não existe", id ));
	}
	
	
}
