package com.nutricao.aplicacaonutricao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.PacienteService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

//@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = PacientesController.class)
//@ExtendWith(SpringExtension.class) 
//@ContextConfiguration 
public class PacientesControllerTest {


	@Autowired
	MockMvc mvc;
	@MockBean
	PacienteService service;
	
	Long idpaciente;
	@BeforeEach
	void setup() {
		idpaciente=1L;
	}

	@Test
	@WithMockUser
	void retornaPaginaPacientes() throws Exception {
		PacientesController controller = new PacientesController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		
		
		MvcResult result = mockMvc.perform(get("/pacientes/crud/acessar")).andReturn();
		
		assertEquals(PAGINAS.PAGINA_PACIENTES,result.getResponse().getForwardedUrl());
	}
	@Test
	@WithMockUser
	void salvarRetornaStatusOkStringSalvo() throws Exception {
		Model model = mock(Model.class);
		PacientesController controller = new PacientesController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		
		Long idpaciente = 1L;

		PacienteDTO pacienteDTO = new PacienteDTO();

		pacienteDTO.setId(1L);

		
		
		MvcResult result = mockMvc.perform(post("/pacientes/crud")
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonMapper.mapearJson(pacienteDTO))).andReturn();

		
		assertEquals("salvo",result.getResponse().getContentAsString());
		assertEquals(201,result.getResponse().getStatus());

	}
//
	@Test
	@WithMockUser
	void pagientePorIdRetornaPaginaPerfilEAtributoPaciente() throws Exception {
		PacientesController controller = new PacientesController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		Long idpaciente = 1L;
		PacienteDTO pacienteDTO = new PacienteDTO();
		pacienteDTO.setId(1L);
		
		when(service.findPacienteById(idpaciente))
				.thenReturn(pacienteDTO);
		 mockMvc.perform(get("/pacientes/crud/" + idpaciente))
		 		.andExpect(status().isOk())
		 		.andExpect(view().name(PAGINAS.PAGINA_PERFIL_PACIENTE))
		 		.andExpect(model().attributeExists("paciente"));
				
		

	}
	
	@Test
	@WithMockUser
	void todosPacientesRetornaPaginaTamanho3() throws Exception {
		PacientesController controller = new PacientesController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		Long idpaciente = 1L;
		PacienteDTO medicaoDTO = new PacienteDTO();
		medicaoDTO.setId(1L);
		PacienteDTO medicaoDTO2 = new PacienteDTO();
		medicaoDTO2.setId(2L);
		PacienteDTO medicaoDTO3 = new PacienteDTO();
		medicaoDTO3.setId(3L);
		List<PacienteDTO> of = List.of(medicaoDTO,medicaoDTO2,medicaoDTO3);
		
		
		when(service.findAllPaciente(PageRequest.of(0, 3)))
				.thenReturn(new PageImpl<>(of));
		
		
		
		mockMvc.perform(get("/pacientes/crud")
				.param("page", "0")
				.param("size", "3"))
		 		.andExpect(status().isOk())
				.andExpect(jsonPath("$.size").value(3));
	}
	
	@Test
	@WithMockUser
	void deleteByIDRetornaOkStatusOk() throws Exception {
		PacientesController controller = new PacientesController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		 MvcResult result = mockMvc.perform(delete("/pacientes/crud/" + 3L)).andReturn();
		 		
		 
		 assertEquals("Paciente Removido Com Sucesso.",result.getResponse().getContentAsString());
		 assertEquals(200,result.getResponse().getStatus());

	}
	
	@Test
	@WithMockUser
	void putRetornaOkStatusOkMudancasSalvas() throws Exception {
		PacientesController controller = new PacientesController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		PacienteDTO medicaoDTO2 = new PacienteDTO();
		medicaoDTO2.setId(2L);
		 MvcResult result = mockMvc.perform(put("/pacientes/crud")
				 	.contentType(MediaType.APPLICATION_JSON)
				 	.content(JsonMapper.mapearJson(medicaoDTO2))).andReturn();
		 		
		 
		 assertEquals("Mudanças realizadas com sucesso.",result.getResponse().getContentAsString());
		 assertEquals(200,result.getResponse().getStatus());

	}

}
