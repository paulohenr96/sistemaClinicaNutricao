package com.nutricao.aplicacaonutricao.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.DietaService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

//@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = DietaController.class)
//@ExtendWith(SpringExtension.class) 
//@ContextConfiguration 
public class DietaControllerTest {


	@Autowired
	MockMvc mvc;
	@MockBean
	DietaService service;
	
	Long idpaciente;
	@BeforeEach
	void setup() {
		idpaciente=1L;
	}

	@Test
	@WithMockUser
	void verDietaRetornaPaginaDietaPaciente() throws Exception {
		mvc.perform(get("/dietas"+"/"+idpaciente)).andExpect(view().name(PAGINAS.PAGINA_DIETA_PACIENTE));

	}
//
//	@Test
//	@WithMockUser
//	void verConsultaRetornaPaginaDieta() throws Exception {
//		Model model = mock(Model.class);
//		Long idpaciente = 1L;
//		mvc.perform(get("/consultas/" + idpaciente)).andExpect(status().isOk())
//				.andExpect(view().name(PAGINAS.PAGINA_DIETA_PACIENTE)).andExpect(model().attributeExists("paciente"));
//
//	}
//
	@Test
	@WithMockUser
	void adicionarRefeicaoRetornaOkString() throws Exception {
		Model model = mock(Model.class);

		Long idpaciente = 1L;

		RefeicaoDTO refeicaoDTO = new RefeicaoDTO();

		refeicaoDTO.setId(1L);

		when(service.novaRefeicao(refeicaoDTO, idpaciente)).thenReturn("saida");
		
		
		mvc.perform(post("/dietas/" + idpaciente).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON).content(JsonMapper.mapearJson(refeicaoDTO)))
				.andExpect(status().isOk()).andExpect(content().string("saida"));

	}
//
	@Test
	@WithMockUser
	void todasAsRefsRetornaOkString() throws Exception {
		Long idpaciente = 1L;
		RefeicaoDTO refeicaoDTO = new RefeicaoDTO();

		refeicaoDTO.setId(1L);

		when(service.todasAsRefeicoesEmJson(idpaciente)).thenReturn("saida");
		mvc.perform(get("/dietas/" + idpaciente+"/crud")).andExpect(status().isOk())
				.andExpect(content().string("saida"));

	}
	
	@Test
	@WithMockUser
	void findRefeicaoByIdRetornaOkString() throws Exception {
		Long idrefeicao = 1L;
		RefeicaoDTO refeicaoDTO = new RefeicaoDTO();

		refeicaoDTO.setId(1L);

		when(service.findRefeicaoById(idrefeicao)).thenReturn("saida");
		mvc.perform(get("/dietas/refeicao/" + idrefeicao)).andExpect(status().isOk())
				.andExpect(content().string("saida"));

	}
//	
//	@Test
//	@WithMockUser
//	void todasConsultaStatusOkLista() throws Exception {
//		ConsultaDTO consultaDTO = new ConsultaDTO();
//
//		consultaDTO.setId(1L);
//		String data="15/07/2023";
//		when(service.findConsultaByData(any(LocalDate.class))).thenReturn(List.of(consultaDTO));
//		mvc.perform(get("/consultas/todos").param("data", data)).andExpect(status().isOk())
//				.andExpect(jsonPath("$").isArray());
//
//	}
//	
//	@Test
//	@WithMockUser
//	void cancelarConsultaRetornaOk() throws Exception {
//		Long id=1L;
//		mvc.perform(put("/consultas/cancelada/"+id).with(SecurityMockMvcRequestPostProcessors.csrf()))
//				.andExpect(status().isOk())
//				.andExpect(content().string("ok"));
//
//	}
//
//	@Test
//	@WithMockUser
//	void finalizarConsultaRetornaOk() throws Exception {
//		Long id=1L;
//		mvc.perform(put("/consultas/realizada/"+id).with(SecurityMockMvcRequestPostProcessors.csrf()))
//				.andExpect(status().isOk())
//				.andExpect(content().string("ok"));
//
//	}
//	
//	@Test
//	@WithMockUser
//	void todasConsultasPaciente() throws Exception {
//		
//		
//		ConsultaDTO consultaDTO = new ConsultaDTO();
//		consultaDTO.setId(1L);
//		ConsultaDTO consultaDTO2 = new ConsultaDTO();
//		consultaDTO2.setId(1L);
//		ConsultaDTO consultaDTO3 = new ConsultaDTO();
//		consultaDTO3.setId(1L);
//		List<ConsultaDTO> lista = List.of(consultaDTO,consultaDTO2,consultaDTO3);
//		
//		Long idPaciente=1L;
//		
//		when(service.findAllConsultaByPaciente(anyLong(),any(PageRequest.class))).thenReturn(new PageImpl(lista));
//		mvc.perform(get("/consultas/todas/"+idPaciente)
//				.param("size", "3")
//				.param("page", "0"))
//		.andExpect(status().isOk())
//				.andExpect(jsonPath("$.content.length()").value(3));
//		
//		verify(service,times(1)).findAllConsultaByPaciente(idPaciente,PageRequest.of(0, 3));
//	}
//	
}
