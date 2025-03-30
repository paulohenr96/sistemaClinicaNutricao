package com.nutricao.aplicacaonutricao.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
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
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricao.aplicacaonutricao.dto.ConsultaDTO;
import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.ConsultaService;
import com.nutricao.aplicacaonutricao.service.PacienteService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

//@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = ConsultaController.class)
//@ExtendWith(SpringExtension.class) 
//@ContextConfiguration 
public class ConsultaControllerTest {


	@Autowired
	MockMvc mvc;
	@MockBean
	ConsultaService service;
	@MockBean
	PacienteService pacienteService;
	
	@BeforeEach
	void setup() {
		Long idPaciente=1L;
		
		PacienteDTO dto = new PacienteDTO();
		dto.setId(idPaciente);
		dto.setNome("João da Silva");
		when(pacienteService.findPacienteById(idPaciente)).thenReturn(dto);
	}

	@Test
	@WithMockUser
	void findAllPacientesRetonaPAGINACONSULTAS() throws Exception {
		Model model = mock(Model.class);
		mvc.perform(get("/consultas")).andExpect(status().isOk()).andExpect(view().name(PAGINAS.PAGINA_CONSULTAS));

	}

	@Test
	@WithMockUser
	void verConsultaRetornaPaginaDieta() throws Exception {
		Model model = mock(Model.class);
		Long idpaciente = 1L;
		mvc.perform(get("/consultas/" + idpaciente)).andExpect(status().isOk())
				.andExpect(view().name(PAGINAS.PAGINA_DIETA_PACIENTE)).andExpect(model().attributeExists("paciente"));

	}

	@Test
	@WithMockUser
	void novaConsultaRetornaVazioStatusOK() throws Exception {
		Model model = mock(Model.class);

		Long idpaciente = 1L;

		ConsultaDTO consultaDTO = new ConsultaDTO();

		consultaDTO.setId(1L);

		mvc.perform(post("/consultas/" + idpaciente).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON).content(JsonMapper.mapearJson(consultaDTO)))
				.andExpect(status().isOk()).andExpect(content().string(""));

	}

	@Test
	@WithMockUser
	void proximaConsultaRetornaOk() throws Exception {
		Long idpaciente = 1L;
		ConsultaDTO consultaDTO = new ConsultaDTO();

		consultaDTO.setId(1L);

		when(service.findProximaConsultaByPaciente(idpaciente)).thenReturn(consultaDTO);
		mvc.perform(get("/consultas/proximaconsulta/" + idpaciente)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"));

	}
	
	@Test
	@WithMockUser
	void todasConsultaStatusOkLista() throws Exception {
		ConsultaDTO consultaDTO = new ConsultaDTO();

		consultaDTO.setId(1L);
		String data="15/07/2023";
		when(service.findConsultaByData(any(LocalDate.class))).thenReturn(List.of(consultaDTO));
		mvc.perform(get("/consultas/todos").param("data", data)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());

	}
	
	@Test
	@WithMockUser
	void cancelarConsultaRetornaOk() throws Exception {
		Long id=1L;
		mvc.perform(put("/consultas/cancelada/"+id).with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(status().isOk())
				.andExpect(content().string("ok"));

	}

	@Test
	@WithMockUser
	void finalizarConsultaRetornaOk() throws Exception {
		Long id=1L;
		mvc.perform(put("/consultas/realizada/"+id).with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(status().isOk())
				.andExpect(content().string("ok"));

	}
	
	@Test
	@WithMockUser
	void todasConsultasPaciente() throws Exception {
		
		
		ConsultaDTO consultaDTO = new ConsultaDTO();
		consultaDTO.setId(1L);
		ConsultaDTO consultaDTO2 = new ConsultaDTO();
		consultaDTO2.setId(1L);
		ConsultaDTO consultaDTO3 = new ConsultaDTO();
		consultaDTO3.setId(1L);
		List<ConsultaDTO> lista = List.of(consultaDTO,consultaDTO2,consultaDTO3);
		
		Long idPaciente=1L;
		
		when(service.findAllConsultaByPaciente(anyLong(),any(PageRequest.class))).thenReturn(new PageImpl(lista));
		mvc.perform(get("/consultas/todas/"+idPaciente)
				.param("size", "3")
				.param("page", "0"))
		.andExpect(status().isOk())
				.andExpect(jsonPath("$.content.length()").value(3));
		
		verify(service,times(1)).findAllConsultaByPaciente(idPaciente,PageRequest.of(0, 3));
	}
	
}
