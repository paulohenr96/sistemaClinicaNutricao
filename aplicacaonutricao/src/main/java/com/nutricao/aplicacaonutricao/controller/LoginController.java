package com.nutricao.aplicacaonutricao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutricao.aplicacaonutricao.dto.LoginDTO;
import com.nutricao.aplicacaonutricao.service.UsuarioService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Controller
@RequestMapping({"/login"})
public class LoginController {

	
	private final UsuarioService service;
	
	@GetMapping
	public String redirecionarLogin(Model model) {
		
		return PAGINAS.PAGINA_LOGIN;
	}
	
	
	@PostMapping
	public ResponseEntity<Object> entrar(Model model,LoginDTO user) {
		
		String retorno=PAGINAS.PAGINA_INDEX;
		retorno=service.login(user);
		
		return ResponseEntity.ok("entrou");
	}
}
