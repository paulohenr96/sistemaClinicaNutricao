package com.nutricao.aplicacaonutricao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nutricao.aplicacaonutricao.calculo.CalcularMacros;
import com.nutricao.aplicacaonutricao.dto.AlimentoRefeicaoDTO;
import com.nutricao.aplicacaonutricao.dto.DietaDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.RefeicaoMapperImp;
import com.nutricao.aplicacaonutricao.model.Alimento;
import com.nutricao.aplicacaonutricao.model.AlimentoRefeicao;
import com.nutricao.aplicacaonutricao.model.Dieta;
import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.model.Refeicao;
import com.nutricao.aplicacaonutricao.repository.AlimentoRefeicaoRepository;
import com.nutricao.aplicacaonutricao.repository.AlimentoRepository;
import com.nutricao.aplicacaonutricao.repository.DietaRepository;
import com.nutricao.aplicacaonutricao.repository.PacienteRepository;
import com.nutricao.aplicacaonutricao.repository.RefeicaoRepository;

@ExtendWith(SpringExtension.class)
public class RefeicaoServiceTest {

	@Mock
	AlimentoRefeicaoRepository alimentoRefeicaoRepository;
	@Mock
	AlimentoRepository alimentoRepository;
	@Mock
	RefeicaoRepository repo;
	@Mock
	DietaRepository dietaRepository;

	@Mock
	PacienteRepository pacienteRepository;
	
	
	@Mock
	RefeicaoMapperImp mapper;

	@InjectMocks
	RefeicaoService service;

	Refeicao r;

	RefeicaoDTO rDTO;

	Dieta d;
	DietaDTO dDTO;
	
	Paciente p;
	
	Alimento a;
	
	AlimentoRefeicao ar;
	AlimentoRefeicaoDTO arDTO;

	
	@BeforeEach
	void setUp() {
		r = new Refeicao();
		rDTO = new RefeicaoDTO();
		
		r.setId(1L);
		r.setNome("nome refeicao");
		rDTO.setId(1L);
		rDTO.setNome("nome refeicao");
		
		d=new Dieta();
		dDTO=new DietaDTO();
		
		d.setId(2L);
		dDTO.setId(2L);
		
		p=new Paciente();
		p.setId(1L);
		
		a=new Alimento();
		a.setId(1L);
		a.setNome("carne");
		
		ar=new AlimentoRefeicao();
		ar.setId(1L);
		a.setPorcao(BigDecimal.valueOf(100));

		a.setCaloria(BigDecimal.valueOf(100));
		a.setCarboidrato(BigDecimal.valueOf(20));
		a.setGordura(BigDecimal.valueOf(50));
		a.setProteina(BigDecimal.valueOf(30));
		ar.setQuantidade(BigDecimal.valueOf(50));
		ar.setAlimento(a);
		ar.setRefeicao(r);
		
		r.getAlimentos().add(ar);
		r.setDieta(d);
		when(mapper.toEntity(rDTO)).thenReturn(r);
		when(mapper.toDTO(r)).thenReturn(rDTO);
		when(dietaRepository.findById(anyLong())).thenReturn(Optional.of(d));
		when(pacienteRepository.findById(anyLong())).thenReturn(Optional.of(p));
		when(repo.findById(anyLong())).thenReturn(Optional.of(r));
		when(alimentoRepository.findById(anyLong())).thenReturn(Optional.of(a));
		when(alimentoRefeicaoRepository.findById(anyLong())).thenReturn(Optional.of(ar));

		
	}
	
	@Test
	void salvarRefeicaoDietaNullSalvaDuasvezes() {
		Dieta dieta=new Dieta();
		dieta.setPaciente(p);
		when(dietaRepository.findByPaciente(p.getId())).thenReturn(null);
		service.salvar(1L,rDTO);
		verify(dietaRepository,times(2)).save(dieta);
	}
	
	@Test
	void salvarRefeicaoDietaNotNullSalvaUmavez() {
		Dieta dieta=new Dieta();
		dieta.setPaciente(p);
		when(dietaRepository.findByPaciente(p.getId())).thenReturn(d);
		service.salvar(1L,rDTO);
		verify(dietaRepository,times(1)).save(d);
	}

	
	
	@Test
	void addalimento () {
		arDTO=new AlimentoRefeicaoDTO();
		arDTO.setAlimento(a.getId());
		arDTO.setQuantidade(BigDecimal.valueOf(50));
		arDTO.setId(1L);
		service.addalimento(r.getId(),arDTO);
		verify(repo,times(1)).save(r);
	}
	
	@Test
	void findAllByDieta() {
		when(repo.findAllByDieta(d.getId())).thenReturn(List.of(r));
		
		List<RefeicaoDTO> sai = service.findAllByDieta(d.getId());
	
		assertTrue(sai.size()==1);
		
	}
	
	@Test
	void findById() {
		
		
		RefeicaoDTO saida = service.findById(1L);
		
		assertEquals(saida.getId(),rDTO.getId());
	}
	
	
	@Test
	void deleteRefeicao() {
		service.deleteRefeicao(1L);
		verify(dietaRepository,times(1)).save(d);
		verify(repo,times(1)).deleteById(1L);
	}
	
	
	@Test
	void removerAlimento() {
		service.removerAlimento(1L);
//		verify(dietaRepository,times(1)).save(d);
		
		
		
		verify(repo,times(1)).save(r);
	}
	
}
