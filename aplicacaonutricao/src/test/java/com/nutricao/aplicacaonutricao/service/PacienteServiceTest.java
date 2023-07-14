package com.nutricao.aplicacaonutricao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.exception.PacienteNotFoundException;
import com.nutricao.aplicacaonutricao.mapper.PacienteMapperImp;
import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.repository.PacienteRepository;

@ExtendWith(SpringExtension.class)
public class PacienteServiceTest {

	
	
	@Mock
	PacienteRepository repo;

	@Mock
	PacienteMapperImp mapper;

	@InjectMocks
	PacienteService service;

	Paciente p;

	PacienteDTO pDTO;

	
	@BeforeEach
	void setUp() {
		p = new Paciente();
		pDTO = new PacienteDTO();
		
		p.setId(1L);
		p.setNome("paulo");
		pDTO.setId(1L);
		pDTO.setNome("paulo");
		
		when(mapper.toEntity(pDTO)).thenReturn(p);
		when(mapper.toDTO(p)).thenReturn(pDTO);

		
	}
	
	@Test
	void salvar() {
		service.salvar(pDTO);
		
		verify(repo,atMostOnce()).save(p);
		
		
	}

	
	
	@Test
	void findPacienteByIdRetornaExcessao() {
		
		when(repo.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThrows(PacienteNotFoundException.class, ()-> service.findPacienteById(1L));
		verify(repo,atMostOnce()).findById(1L);
		verifyNoMoreInteractions(repo);
	}
	
	@Test
	void findPacienteByIdRetornaDTO() {
		
		when(repo.findById(anyLong())).thenReturn(Optional.of(p));
		
		PacienteDTO saida = service.findPacienteById(1L);
		
		assertTrue(p.getId().equals(saida.getId()));
		verify(repo,atMostOnce()).findById(1L);
		verifyNoMoreInteractions(repo);
	}
	
	@Test
	void findAllPAcienteSucesso() {
		when(repo.findAll(any(PageRequest.class))).thenReturn(new PageImpl(List.of(p)));
		Page<PacienteDTO> saida = service.findAllPaciente(PageRequest.of(0, 3));
		assertEquals(saida.getContent().get(0).getId(),p.getId());
	}
	
	@Test
	void findAllByNome() {
		String nome="paulo";
		p.setNome("paulo");
		when(repo.findAllByNome(anyString(),any(PageRequest.class))).thenReturn(new PageImpl(List.of(p)));
		
		
		Page<PacienteDTO> saida = service.findAllByNome(nome,PageRequest.of(0, 3));
		assertTrue(saida.getContent().get(0).getNome().equals(nome));
	}
	
	
	@Test
	void deletePacienteSemExcessao() {
		service.deletePaciente(1L);
		
		verify(repo,atMostOnce()).deleteById(1L);
		
		
	}
	@Test
	void deletePacienteExcessao() {
		
		   doThrow(new IllegalArgumentException()).when(repo).deleteById(1L);

		
		assertThrows(PacienteNotFoundException.class,()->service.deletePaciente(1L));
		
		
		
		
	}
	
	
	@Test
	void updatePacienteExcessao() {
		
		when(repo.findById(anyLong())).thenReturn(Optional.of(p));
		
		HttpStatus saida = service.updatePaciente(pDTO);
		
		assertEquals(saida,HttpStatus.OK);
		verify(repo,atMostOnce()).findById(pDTO.getId());
		verify(repo,atMostOnce()).save(p);

		
		
		
	}
	
	
}
