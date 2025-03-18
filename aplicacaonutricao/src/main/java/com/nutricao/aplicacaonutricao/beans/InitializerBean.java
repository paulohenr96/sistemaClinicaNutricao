package com.nutricao.aplicacaonutricao.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.model.Usuario;
import com.nutricao.aplicacaonutricao.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
@Component
public class InitializerBean {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostConstruct
	public void createData() {
		
		
		if(usuarioRepository.findByUsername("admin").isEmpty()) {
			Usuario user=new Usuario();
			
			user.setUsername("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			
			usuarioRepository.save(user);
		
		}
	}
	
}
