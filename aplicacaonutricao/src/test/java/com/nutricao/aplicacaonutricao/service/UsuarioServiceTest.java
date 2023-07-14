package com.nutricao.aplicacaonutricao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nutricao.aplicacaonutricao.dto.LoginDTO;
import com.nutricao.aplicacaonutricao.dto.UsuarioDTO;
import com.nutricao.aplicacaonutricao.mapper.UsuarioMapperImp;
import com.nutricao.aplicacaonutricao.model.Usuario;
import com.nutricao.aplicacaonutricao.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {

	@Mock
	UsuarioRepository repository;

	@Mock
	UsuarioMapperImp mapper;
	
	@InjectMocks
	UsuarioService service;
	
	UsuarioDTO usuarioDTO;
	Usuario usuario;
	
	@BeforeEach
	void setUp() {
		usuarioDTO=new UsuarioDTO();
		usuarioDTO.setId(1L);
		usuarioDTO.setNome("paulo");
		
		usuario=new Usuario();
		usuario.setId(1L);
		usuario.setNome("paulo");
		
//        MockitoAnnotations.openMocks(this);

		
	}
	
	@Test
	void findUsuario() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("paulo");
		when(repository.findByUsername(anyString())).thenReturn(Optional.of(usuario));

		when(mapper.toDTO(any(Usuario.class))).thenReturn(usuarioDTO);
		

		UsuarioDTO saida = service.findUsuario();
		assertEquals(saida,usuarioDTO);
		
		
		
	}
	
	@Test
	void loginUsernamInexistenteJogaExcessao() {
		when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
		LoginDTO loginDTO=new LoginDTO();
		loginDTO.setUsername("admin");
		loginDTO.setPassword("admin");
		assertThrows(RuntimeException.class,()->service.login(loginDTO));
		
		
		
	}
	@Test
	void loginSenhaErradaJogaExcessao() {
		usuario.setPassword(new BCryptPasswordEncoder().encode("1234"));

		when(repository.findByUsername(anyString())).thenReturn(Optional.of(usuario));
		LoginDTO loginDTO=new LoginDTO();
		loginDTO.setUsername("admin");
		loginDTO.setPassword("admin");
		
		BCryptPasswordEncoder bcrypt = mock(BCryptPasswordEncoder.class);
		when(bcrypt.matches(anyString(), anyString())).thenReturn(false);
		assertThrows(RuntimeException.class,()->service.login(loginDTO));

		
	}
	
	@Test
	void loginSucesso() {
		
		usuario.setPassword(new BCryptPasswordEncoder().encode("admin"));
		when(repository.findByUsername(anyString())).thenReturn(Optional.of(usuario));
		LoginDTO loginDTO=new LoginDTO();
		loginDTO.setUsername("admin");
		loginDTO.setPassword("admin");
		
		SecurityContext contexto = mock(SecurityContext.class);
		SecurityContextHolder.setContext(contexto);
		String saida = service.login(loginDTO);
		assertEquals(saida,"ok");
	}
	
	@Test
	void updateUsuario() {
		when(repository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
		
		service.updateUsuario(usuarioDTO);
		
		verify(repository,times(1)).save(usuario);
	}
	
}
