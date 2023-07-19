package com.nutricao.aplicacaonutricao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.nutricao.aplicacaonutricao.dto.UsuarioDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.UsuarioService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

//@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = UsuarioController.class)
//@ExtendWith(SpringExtension.class) 
//@ContextConfiguration 
public class UsuarioControllerTest {


	@Autowired
	MockMvc mvc;
	@MockBean
	UsuarioService service;
	
	Long idpaciente;
	@BeforeEach
	void setup() {
		idpaciente=1L;
	}

	@Test
	@WithMockUser
	void paginaUsuarioRetornaPaginaUsuarioPaciente() throws Exception {
		UsuarioController controller = new UsuarioController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		
		
		UsuarioDTO dto=new UsuarioDTO();
		dto.setId(3L);
		
		when(service.findUsuario()).thenReturn(dto);
		
		MvcResult result = mockMvc.perform(get("/usuario/acessar")).andReturn();
		
		assertEquals(PAGINAS.PAGINA_PERFIL,result.getResponse().getForwardedUrl());
		assertTrue(result.getRequest().getAttribute("usuario")!=null);
	}
	@Test
	@WithMockUser
	void editaUsuarioRetornaStringComStatusOk() throws Exception {
		UsuarioController controller = new UsuarioController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		UsuarioDTO dto = new UsuarioDTO();

		dto.setId(1L);
		 MvcResult result = mockMvc.perform(put("/usuario")
				 	.contentType(MediaType.APPLICATION_JSON)
				 	.content(JsonMapper.mapearJson(dto))).andReturn();
		 		
		 
		 assertEquals("Mudanças realizadas com sucesso.",result.getResponse().getContentAsString());
		 assertEquals(200,result.getResponse().getStatus());

	}

}
