package com.nutricao.aplicacaonutricao.exception;

public class PacienteNotFoundException extends RuntimeException{

	public PacienteNotFoundException(Long id) {

		super(String.format("O paciente com id %d não existe", id ));
	}
	
	
}
