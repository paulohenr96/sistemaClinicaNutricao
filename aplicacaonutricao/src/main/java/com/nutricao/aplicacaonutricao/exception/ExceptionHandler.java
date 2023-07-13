package com.nutricao.aplicacaonutricao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	@org.springframework.web.bind.annotation.ExceptionHandler (RuntimeException.class)
	public ResponseEntity<String> handlRuntimeException(RuntimeException ex) {
		// TODO Auto-generated method stub
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler (Exception.class)
	public ResponseEntity<String> handlException(Exception ex) {
		// TODO Auto-generated method stub
		return new ResponseEntity<String>("Ocorreu um erro interno.",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler (InternalAuthenticationServiceException.class)
	public ResponseEntity<String> handlSecurity(InternalAuthenticationServiceException ex) {
		// TODO Auto-generated method stub
		return new ResponseEntity<String>("Ocorreu um erro de autenticação.",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
