package com.nutricao.aplicacaonutricao.controller;

import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nutricao.aplicacaonutricao.util.PAGINAS;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	@GetMapping("/sair")
	public String logout(HttpServletRequest request, HttpServletResponse response,ModelAndView model) {
		System.out.println("sair....");
		
	    CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
	    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
	    cookieClearingLogoutHandler.logout(request, response, null);
	    securityContextLogoutHandler.logout(request, response, null);
	    
		
		request.setAttribute("logout", true);
		return "login";
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
