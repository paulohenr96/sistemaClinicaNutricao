package com.nutricao.aplicacaonutricao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nutricao.aplicacaonutricao.dto.UsuarioDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.UsuarioService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

//@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = WelcomeController.class)
//@ExtendWith(SpringExtension.class) 
//@ContextConfiguration 
public class WelcomeControllerTest {


	MockMvc mockMvc;
	@MockBean
	UsuarioService service;
	
	Long idpaciente;
	@BeforeEach
	void setup() {
		idpaciente=1L;
	
		WelcomeController controller = new WelcomeController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}
	
	@Test
	@WithMockUser
	void inicialRetornaIndex() throws Exception {
		MvcResult result = mockMvc.perform(get("/home")).andReturn();
		assertEquals(PAGINAS.PAGINA_INDEX,result.getResponse().getForwardedUrl());
	}
	
	@Test
	@WithMockUser
	void postPaginaInicialLoginCorreto() throws Exception {
		MvcResult result = mockMvc.perform(post("/home")).andReturn();
		assertEquals(PAGINAS.PAGINA_INDEX,result.getResponse().getForwardedUrl());
	}
	
	@Test
	@WithMockUser
	void paginaPacientes() throws Exception {
		MvcResult result = mockMvc.perform(get("/pacientes")).andReturn();
		assertEquals(PAGINAS.PAGINA_PACIENTES,result.getResponse().getForwardedUrl());
	}
	
	
	
//	@Test
//	@WithMockUser
//	void editaUsuarioRetornaStringComStatusOk() throws Exception {
//
//		 MvcResult result = mockMvc.perform(put("/usuario")
//				 	.contentType(MediaType.APPLICATION_JSON)
//				 	.content(JsonMapper.mapearJson(dto))).andReturn();
//		 		
//		 
//		 assertEquals("Mudanças realizadas com sucesso.",result.getResponse().getContentAsString());
//		 assertEquals(200,result.getResponse().getStatus());
//
//	}

}
