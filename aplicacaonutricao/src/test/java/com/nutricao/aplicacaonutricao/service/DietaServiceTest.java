package com.nutricao.aplicacaonutricao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nutricao.aplicacaonutricao.dto.DietaDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.DietaMapperImp;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.mapper.RefeicaoMapperImp;
import com.nutricao.aplicacaonutricao.model.Dieta;
import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.model.Refeicao;
import com.nutricao.aplicacaonutricao.repository.DietaRepository;
import com.nutricao.aplicacaonutricao.repository.PacienteRepository;
import com.nutricao.aplicacaonutricao.repository.RefeicaoRepository;

@ExtendWith(SpringExtension.class)

public class DietaServiceTest {

	@Mock
	DietaRepository repo;
	@Mock
	PacienteRepository repoPaciente;
	@Mock
	RefeicaoRepository repoRefeicao;

	@Mock
	DietaMapperImp mapper;
	@Mock
	RefeicaoMapperImp mapperRefeicao;


	@InjectMocks
	DietaService service;

	Dieta d;

	DietaDTO dDTO;
	
	Paciente p;

	Refeicao r;
	RefeicaoDTO rDTO;

	@BeforeEach
	void setUp() {
		d = new Dieta();
		dDTO = new DietaDTO();
		r = new Refeicao();
		rDTO = new RefeicaoDTO();
		p=new Paciente();
		d.setRefeicoes(new ArrayList<>());

		
		p.setId(1L);
		d.setId(1L);
		dDTO.setId(1L);
		r.setId(1L);
		rDTO.setId(1L);
		
		when(mapperRefeicao.toDTO(r)).thenReturn(rDTO);
		when(mapper.toDTO(d)).thenReturn(dDTO);
		when(mapper.toEntity(dDTO)).thenReturn(d);
		when(mapperRefeicao.toEntity(rDTO)).thenReturn(r);
		
		when(repo.findByPaciente(anyLong())).thenReturn(d);
		when(repoRefeicao.findById(anyLong())).thenReturn(Optional.of(r));
		when(repoPaciente.findById(anyLong())).thenReturn(Optional.of(p));
	}

	@Test
	void salvaComSucesso() {
		
		service.salvar(dDTO);
		
		verify(mapper).toEntity(dDTO);
		verify(repo).save(d);

	}
	
	@Test
	void findDietaByPacienteRetornaDTO() {
		
		DietaDTO saida = service.findDietaByPaciente(p.getId());
		
		assertEquals(saida,dDTO);
		
		verify(mapper).toDTO(d);

		
	}
	
	@Test
	void findDietaByPacienteRetornaNULL() {
		when(repo.findByPaciente(anyLong())).thenReturn(null);

		DietaDTO saida = service.findDietaByPaciente(p.getId());
		
		assertEquals(saida,null);
		

		
	}
	
	@Test
	public void deleteDieta() {
			
			service.deleteDieta(d.getId());
			verify(repo).deleteById(d.getId());
	}
	
	@Test
	void novaRefeicaoSucesso() {
		String s=service.novaRefeicao(rDTO, p.getId());
		assertTrue(s.length()>0);
		
		verify(repo, atMostOnce()).findByPaciente(p.getId());
		verify(repo, atMostOnce()).save(d);
		verify(repoPaciente,never()).findById(p.getId());
		verify(repoRefeicao, atMostOnce()).save(r);
		
	}
	
	@Test
	void novaRefeicaoDietaNull() {
		when(repo.findByPaciente(anyLong())).thenReturn(null);
		
		String s=service.novaRefeicao(rDTO, p.getId());
		
		
		
		assertTrue(s.length()>0);
		verify(repoPaciente,atMostOnce()).findById(p.getId());
		verify(repo, atMostOnce()).findByPaciente(p.getId());
		verify(repo, atMostOnce()).save(d);
		verify(repoRefeicao, atMostOnce()).save(r);
		
	}
	
	@Test
	void todasAsRefeicoesEmJsonSaidaDiferenteDeNull() {
		
	String saida = service.todasAsRefeicoesEmJson(p.getId());
	
	assertEquals(saida,JsonMapper.mapearJson(new ArrayList<>()));
	verify(repo, atMostOnce()).findByPaciente(p.getId());

	
	
	
	}
	
	
	@Test
	void todasAsRefeicoesEmJsonSaidaNull() {
		when(repo.findByPaciente(anyLong())).thenReturn(null);

	String saida = service.todasAsRefeicoesEmJson(p.getId());
	
	assertTrue(saida.equals(JsonMapper.mapearJson(new ArrayList<>())));
	verify(repo, atMostOnce()).findByPaciente(p.getId());

	
	
	
	}
	
	
	@Test
	void findRefeicaoById() {
		service.findRefeicaoById(r.getId());
		
		verify(repo,atMostOnce()).findById(r.getId());
		verify(mapperRefeicao,atMostOnce()).toDTO(r);

		
	}
	
	


	
}
