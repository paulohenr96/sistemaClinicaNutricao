package com.nutricao.aplicacaonutricao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutricao.aplicacaonutricao.dto.LoginDTO;
import com.nutricao.aplicacaonutricao.service.UsuarioService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;
@Controller
@RequestMapping("/login")
public class LoginController {

	
	private final UsuarioService service;
	
	
	public LoginController (UsuarioService service) {
		this.service=service;
	}
	
	@GetMapping
	public String redirecionarLogin(Model model) {
		
		return PAGINAS.PAGINA_LOGIN;
	}
	
	
	@PostMapping
	public ResponseEntity<Object> entrar(Model model,@RequestBody LoginDTO user) {
		
		service.login(user);
		
		return ResponseEntity.ok("entrou");
	}
}
