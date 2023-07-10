package com.nutricao.aplicacaonutricao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;
@Controller
public class WelcomeController {

	@GetMapping("/index")
	public String greeting(ModelAndView model) {
		
		return "index.html";
	}
	@GetMapping("/")
	public String inicial(ModelAndView model) {
		
		return "consultas.html";
	}
	@GetMapping("/alimentos")
	public String paginaAlimentos(ModelAndView model) {
		
		return "alimentos.html";
	}
	
	
	@GetMapping("/pacientes")
	public String paginaPacientes(ModelAndView model) {
		
		return "pacientes.html";
	}
}
