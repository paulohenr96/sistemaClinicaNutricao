package com.nutricao.aplicacaonutricao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nutricao.aplicacaonutricao.dto.AlimentoRefeicaoDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.RefeicaoService;

//@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = RefeicaoController.class)
//@ExtendWith(SpringExtension.class) 
//@ContextConfiguration 
public class RefeicaoControllerTest {


	@Autowired
	MockMvc mockMvc;
	@MockBean
	RefeicaoService service;
	
	Long idpaciente;
	@BeforeEach
	void setup() {
		idpaciente=1L;
		
		RefeicaoController controller = new RefeicaoController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	@WithMockUser
	void adicionarAlimentoRefeicaoRetornaOkStatusOk() throws Exception {

		
		Long refeicao = 1L;

		AlimentoRefeicaoDTO dto = new AlimentoRefeicaoDTO();

		dto.setId(1L);

		
		
		MvcResult result = mockMvc.perform(post("/refeicao/"+refeicao)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonMapper.mapearJson(dto))).andReturn();

		
		assertEquals("ok",result.getResponse().getContentAsString());
		assertEquals(200,result.getResponse().getStatus());
		verify(service).addalimento(refeicao,dto);

	}
	
	@Test
	@WithMockUser
	void adicionarRefeicaoRetornaOkStatusOk() throws Exception {

		
		Long paciente = 1L;

		RefeicaoDTO dto = new RefeicaoDTO();

		dto.setId(1L);

		
		
		MvcResult result = mockMvc.perform(post("/refeicao/nova/"+paciente)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonMapper.mapearJson(dto))).andReturn();

		
		assertEquals("ok",result.getResponse().getContentAsString());
		assertEquals(200,result.getResponse().getStatus());
		verify(service).salvar(paciente,dto);

	}
//
	@Test
	@WithMockUser
	void findRefeicaoByIdRetonaObjetoDTO() throws Exception {
		
		RefeicaoDTO r=new RefeicaoDTO();
		r.setId(3L);
		
		when(service.findById(anyLong()))
				.thenReturn(r);
		
		
		
		mockMvc.perform(get("/refeicao/"+3L))
				
		 		.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3));
	}
	
	@Test
	@WithMockUser
	void deleteAlimentoRetornaOkStatusOk() throws Exception {
		
		AlimentoRefeicaoDTO a=new AlimentoRefeicaoDTO();
		a.setId(3L);
		 MvcResult result = mockMvc.perform(delete("/refeicao/alimento/" + 3L)
				 .with(SecurityMockMvcRequestPostProcessors.csrf()))
				 .andReturn();
		 
		 
		 
		 assertEquals("ok",result.getResponse().getContentAsString());
		 assertEquals(200,result.getResponse().getStatus());
		 verify(service,times(1)).removerAlimento(a.getId());
	}
	@Test
	@WithMockUser
	void deleteRefeicaoRetornaOkStatusOk() throws Exception {
	
		RefeicaoDTO r=new RefeicaoDTO();
		r.setId(3L);
		 MvcResult result = mockMvc.perform(delete("/refeicao/" + 3L)
				 
				 .with(SecurityMockMvcRequestPostProcessors.csrf()))
				 
				 .andReturn();
		 		
		 
		 assertEquals("ok",result.getResponse().getContentAsString());
		 assertEquals(200,result.getResponse().getStatus());
		 verify(service,times(1)).deleteRefeicao(r.getId());

	}
	

}
