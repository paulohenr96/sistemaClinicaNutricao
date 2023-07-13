package com.nutricao.aplicacaonutricao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nutricao.aplicacaonutricao.dto.LoginDTO;
import com.nutricao.aplicacaonutricao.dto.UsuarioDTO;
import com.nutricao.aplicacaonutricao.mapper.UsuarioMapperImp;
import com.nutricao.aplicacaonutricao.model.Usuario;
import com.nutricao.aplicacaonutricao.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final UsuarioMapperImp mapper;
	public UsuarioDTO findUsuario() {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Usuario> optional = repository.findByUsername(name);
		
		return mapper.toDTO(optional.get());
	}

	public String login(LoginDTO login) {

		Optional<Usuario> optional = repository.findByUsername(login.getUsername());
		if (optional.isEmpty()) {
			SecurityContextHolder.clearContext();
			throw new RuntimeException("Nome ou senha incorretos");
		}
		Usuario usuario = optional.get();

		boolean senha = new BCryptPasswordEncoder().matches(login.getPassword(), usuario.getPassword());
		if (!senha) {
			SecurityContextHolder.clearContext();
			throw new RuntimeException("Nome ou senha incorretos");
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				login.getUsername(), "", List.of(new SimpleGrantedAuthority("USER")));

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		return "ok";

	}

	public void updateUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		
		
		Usuario user = repository.findById(usuario.getId()).get();
	
		
		user.setNome(usuario.getNome());
		user.setFoto(usuario.getFoto());
		user.setTelefone(usuario.getTelefone());
		user.setEndereco(usuario.getEndereco());
		
		
		repository.save(user);
		
	}
}
