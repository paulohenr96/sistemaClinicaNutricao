package com.nutricao.aplicacaonutricao.exception;

public class AlimentoNotFoundException extends RuntimeException{

	public AlimentoNotFoundException(Long id) {

		super(String.format("O alimento com id %d não existe", id ));
	}
	
	
}
