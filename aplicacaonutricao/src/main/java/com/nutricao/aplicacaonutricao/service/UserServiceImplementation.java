package com.nutricao.aplicacaonutricao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nutricao.aplicacaonutricao.model.Usuario;
import com.nutricao.aplicacaonutricao.repository.UsuarioRepository;

public class UserServiceImplementation implements UserDetailsService{

	private final UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<Usuario> optional=repository.findByUsername(username);
		
		if (optional.isEmpty()) {
			throw new RuntimeException("Usuario e senha incorretos");
		}
		Usuario usuario = optional.get();
		UserDetails user=new User(usuario.getUsername(),usuario.getPassword(),List.of(new SimpleGrantedAuthority("USER")));
		
		return user;
	}

	public UserServiceImplementation(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

}
