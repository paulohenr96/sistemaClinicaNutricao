package com.nutricao.aplicacaonutricao.exception;

public class RefeicaoNotFoundException extends RuntimeException{

	public RefeicaoNotFoundException(Long id) {

		super(String.format("A refeicao com id %d não existe", id ));
	}
	
	
}
