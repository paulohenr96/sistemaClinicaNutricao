package com.nutricao.aplicacaonutricao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.nutricao.aplicacaonutricao.dto.MedicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.MedicaoService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

//@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = MedicaoController.class)
//@ExtendWith(SpringExtension.class) 
//@ContextConfiguration 
public class MedicaoControllerTest {


	@Autowired
	MockMvc mvc;
	@MockBean
	MedicaoService service;
	
	Long idpaciente;
	@BeforeEach
	void setup() {
		idpaciente=1L;
	}

	@Test
	@WithMockUser
	void paginaMedicaoRetornaPaginaMedicaoPaciente() throws Exception {
		MedicaoController controller = new MedicaoController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		
		
		MvcResult result = mockMvc.perform(get("/medicoes"+"/"+idpaciente)).andReturn();
		
		assertEquals(PAGINAS.PAGINA_MEDICAO_PACIENTE,result.getResponse().getForwardedUrl());
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
	void adicionarMedicaoRetornaOkStatusOk() throws Exception {
		Model model = mock(Model.class);
		MedicaoController controller = new MedicaoController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		
		Long idpaciente = 1L;

		MedicaoDTO medicaoDTO = new MedicaoDTO();

		medicaoDTO.setId(1L);

		
		
		MvcResult result = mockMvc.perform(post("/medicoes/" + idpaciente)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonMapper.mapearJson(medicaoDTO))).andReturn();

		
		assertEquals("ok",result.getResponse().getContentAsString());
		assertEquals(200,result.getResponse().getStatus());

	}
//
	@Test
	@WithMockUser
	void todasMedicoesPaginadasRetornaPageableOk() throws Exception {
		MedicaoController controller = new MedicaoController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		Long idpaciente = 1L;
		MedicaoDTO medicaoDTO = new MedicaoDTO();
		medicaoDTO.setId(1L);
		MedicaoDTO medicaoDTO2 = new MedicaoDTO();
		medicaoDTO2.setId(2L);
		MedicaoDTO medicaoDTO3 = new MedicaoDTO();
		medicaoDTO3.setId(3L);
		List<MedicaoDTO> of = List.of(medicaoDTO,medicaoDTO2,medicaoDTO3);
		when(service.findAllMedicaoPaginado(idpaciente,PageRequest.of(0,3)))
				.thenReturn(new PageImpl<>(of));
		 mockMvc.perform(get("/medicoes/" + idpaciente+"/crud")
				.param("page", "0")
				.param("size", "3"))
		 		.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray());
		

	}
	
	@Test
	@WithMockUser
	void todasAsMedicoesPorGraficoRetornaPaginaTamanho3() throws Exception {
		MedicaoController controller = new MedicaoController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		Long idpaciente = 1L;
		MedicaoDTO medicaoDTO = new MedicaoDTO();
		medicaoDTO.setId(1L);
		MedicaoDTO medicaoDTO2 = new MedicaoDTO();
		medicaoDTO2.setId(2L);
		MedicaoDTO medicaoDTO3 = new MedicaoDTO();
		medicaoDTO3.setId(3L);
		List<MedicaoDTO> of = List.of(medicaoDTO,medicaoDTO2,medicaoDTO3);
		
		
		when(service.findAllMedicaoOrdenada(idpaciente,"data"))
				.thenReturn(new PageImpl<>(of));
		
		
		
		mockMvc.perform(get("/medicoes/" + idpaciente+"/data")
				.param("page", "0")
				.param("size", "3"))
		 		.andExpect(status().isOk())
				.andExpect(jsonPath("$.size").value(3));
	}
	
	@Test
	@WithMockUser
	void deleteByIDRetornaOkStatusOk() throws Exception {
		MedicaoController controller = new MedicaoController(service);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		 MvcResult result = mockMvc.perform(delete("/medicoes/" + 3L)).andReturn();
		 		
		 
		 assertEquals("ok",result.getResponse().getContentAsString());
		 assertEquals(200,result.getResponse().getStatus());

	}

}
