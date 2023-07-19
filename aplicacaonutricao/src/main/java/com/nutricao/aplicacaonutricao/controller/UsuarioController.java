package com.nutricao.aplicacaonutricao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutricao.aplicacaonutricao.dto.UsuarioDTO;
import com.nutricao.aplicacaonutricao.service.UsuarioService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	
	
	private final UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	
	
	@GetMapping("acessar")
	public String redirecionar(Model model) {

		UsuarioDTO usuario = service.findUsuario();
		model.addAttribute("usuario",usuario);
		return PAGINAS.PAGINA_PERFIL;
	}
	
	
	
	@PutMapping
	public ResponseEntity<String> editar(Model model,@RequestBody UsuarioDTO usuario) {
		service.updateUsuario(usuario);
		return ResponseEntity.ok("Mudanças realizadas com sucesso.");
	}
	
}
