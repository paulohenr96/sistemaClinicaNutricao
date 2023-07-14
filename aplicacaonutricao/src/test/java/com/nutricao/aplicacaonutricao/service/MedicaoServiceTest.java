package com.nutricao.aplicacaonutricao.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nutricao.aplicacaonutricao.dto.MedicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.MedicaoMapperImp;
import com.nutricao.aplicacaonutricao.model.Medicao;
import com.nutricao.aplicacaonutricao.repository.MedicaoRepository;

@ExtendWith(SpringExtension.class)
public class MedicaoServiceTest {

	
	
	@Mock
	MedicaoRepository repo;

	@Mock
	MedicaoMapperImp mapper;

	@InjectMocks
	MedicaoService service;

	Medicao m;

	MedicaoDTO mDTO;

	
	@BeforeEach
	void setUp() {
		m = new Medicao();
		mDTO = new MedicaoDTO();
		
		m.setId(1L);
		mDTO.setId(1L);
	}

	@Test
	void salvarConsultaEmHorarioReservado() {
		when(repo.existByData(any(LocalDate.class), anyLong())).thenReturn(1);
	
		assertThrows(RuntimeException.class,()->service.salvar(mDTO, 1L));
	
		verify(repo,never()).save(m);
	}
	
	@Test
	void salvarConsultaEmHorarioDisponivel() {
		when(repo.existByData(any(LocalDate.class), anyLong())).thenReturn(0);
	
		assertThrows(RuntimeException.class,()->service.salvar(mDTO, 1L));
	
		verify(repo,atMostOnce()).save(m);
	}
	
	
	@Test
	void findAllMedicaoPaginado()
	{
		PageRequest pageRequest = PageRequest.of(0, 3);
		List<Medicao> lista = List.of(m);
		when(mapper.toDTO(m)).thenReturn(mDTO);
		when(repo.findAllByPaciente(anyLong(),any(PageRequest.class))).thenReturn(new PageImpl(lista));
		Page<MedicaoDTO> saida = service.findAllMedicaoPaginado(1L, pageRequest);
		assertTrue(saida.isEmpty()==false);
		assertTrue(saida.getSize()==1);
		verify(repo,atMostOnce()).findAllByPaciente(1L, pageRequest);
	}
	
	@Test
	void findAllMedicaoOrdenada()
	{
		String coluna="data";
		
		Sort sort = Sort.by(Sort.Direction.ASC, coluna);
		
		List<Medicao> lista = List.of(m);
	
		when(mapper.toDTO(m)).thenReturn(mDTO);
		when(repo.findAllByPaciente(anyLong(),any(PageRequest.class))).thenReturn(new PageImpl(lista));
		
		Page<MedicaoDTO> saida = service.findAllMedicaoOrdenada(1L, "data");
		
		assertTrue(saida.isEmpty()==false);
		assertTrue(saida.getSize()==1);
	}
	
	@Test
	void deleteMedicaoByIDThrows() {
		   doThrow(new RuntimeException()).when(repo).deleteById(1L);
		   assertThrows(RuntimeException.class,()->service.deleteMedicaoByID(1L));
	}
	@Test
	void deleteMedicaoByID() {
		
		service.deleteMedicaoByID(1L);
		
		verify(repo,atMostOnce()).deleteById(1L);
		
	}
	
	
}
