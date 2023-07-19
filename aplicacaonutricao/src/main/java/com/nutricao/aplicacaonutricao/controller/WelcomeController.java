package com.nutricao.aplicacaonutricao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nutricao.aplicacaonutricao.util.PAGINAS;

import ch.qos.logback.core.model.Model;
@Controller
public class WelcomeController {

	
	@GetMapping("/home")
	public String inicial(ModelAndView model) {
		
		return PAGINAS.PAGINA_INDEX;
	}
	@PostMapping("/home")
	public String loginCorreto(ModelAndView model) {
		
		return PAGINAS.PAGINA_INDEX;
	}
//	
//	@GetMapping("/alimentos")
//	public String paginaAlimentos(ModelAndView model) {
//		
//		return PAGINAS.PAGINA_ALIMENTOS;
//	}
	
	
	@GetMapping("/pacientes")
	public String paginaPacientes(ModelAndView model) {
		
		return PAGINAS.PAGINA_PACIENTES;
	}
}
