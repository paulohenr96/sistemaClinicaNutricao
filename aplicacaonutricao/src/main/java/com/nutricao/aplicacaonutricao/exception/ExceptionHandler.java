package com.nutricao.aplicacaonutricao.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	
	@org.springframework.web.bind.annotation.ExceptionHandler (Exception.class)
	public ResponseEntity<String> handlException(Exception ex) {
		// TODO Auto-generated method stub
		return new ResponseEntity<String>("Ocorreu um erro interno.",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
