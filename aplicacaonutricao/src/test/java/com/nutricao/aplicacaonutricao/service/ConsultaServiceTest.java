package com.nutricao.aplicacaonutricao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nutricao.aplicacaonutricao.dto.ConsultaDTO;
import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.mapper.ConsultaMapperImp;
import com.nutricao.aplicacaonutricao.model.Consulta;
import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.repository.ConsultaRepository;

@ExtendWith(SpringExtension.class)
public class ConsultaServiceTest {

	@Mock
	ConsultaRepository repo;

	@Mock
	ConsultaMapperImp mapper;

	@InjectMocks
	ConsultaService service;

	Paciente p;

	PacienteDTO pDTO;

	Consulta c;
	ConsultaDTO cDTO;

	@BeforeEach
	void setUp() {
		p = new Paciente();
		pDTO = new PacienteDTO();
		c = new Consulta();
		cDTO = new ConsultaDTO();

	}

	@Test
	void salvaComSucesso() {
		cDTO.setHorario(LocalDateTime.parse("2016-12-03T10:15"));
		p.setId(1L);

		when(repo.verificaDisponibilidade(cDTO.getHorario())).thenReturn(0);
		when(mapper.toEntity(cDTO)).thenReturn(c);
		service.salvar(cDTO, p.getId());

		verify(repo).verificaDisponibilidade(cDTO.getHorario());

		verify(repo).save(c);
		verifyNoMoreInteractions(repo);

	}

	@Test
	void salvaRetornaRuntimeException() {
		cDTO.setHorario(LocalDateTime.parse("2016-12-03T10:15"));
		p.setId(1L);

		when(repo.verificaDisponibilidade(cDTO.getHorario())).thenReturn(2);
		assertThrows(RuntimeException.class, () -> service.salvar(cDTO, p.getId()));

	}

	@Test
	void findProximaConsultaByPacienteSucesso() {
		cDTO.setId(1L);
		c.setId(1L);
		p.setId(1L);

		LocalDate now = LocalDate.now();

		when(repo.findProximaConsultaByPaciente(anyLong(), any(LocalDate.class))).thenReturn(List.of(c));
		when(mapper.toDTO(c)).thenReturn(cDTO);
		ConsultaDTO saida = service.findProximaConsultaByPaciente(p.getId());

		assertEquals(saida, cDTO);

		verify(repo).findProximaConsultaByPaciente(p.getId(), now);

		verifyNoMoreInteractions(repo);
	}

	@Test
	void findProximaConsultaByPacienteRetornaNull() {
		cDTO.setId(1L);
		c.setId(1L);
		p.setId(1L);

		LocalDate now = LocalDate.now();

		when(repo.findProximaConsultaByPaciente(anyLong(), any(LocalDate.class))).thenReturn(new ArrayList<>());
		when(mapper.toDTO(c)).thenReturn(cDTO);
		ConsultaDTO saida = service.findProximaConsultaByPaciente(p.getId());

		assertEquals(saida, null);

		verify(repo).findProximaConsultaByPaciente(p.getId(), now);

		verifyNoMoreInteractions(repo);
	}

	@Test
	void findConsultaByDataRetornaListaDTO() {
		cDTO.setId(1L);
		c.setId(1L);

		LocalDate now = LocalDate.now();

		when(repo.findAllConsultaData(any(LocalDate.class))).thenReturn(List.of(c));
		when(mapper.toDTO(c)).thenReturn(cDTO);

		List<ConsultaDTO> saida = service.findConsultaByData(now);

		assertEquals(saida, List.of(cDTO));

		verify(repo).findAllConsultaData(now);

		verifyNoMoreInteractions(repo);
	}
	
	@Test
	void findConsultaByDataRetornaListaVazia() {
		cDTO.setId(1L);
		c.setId(1L);

		LocalDate now = LocalDate.now();

		when(repo.findAllConsultaData(any(LocalDate.class))).thenReturn(new ArrayList<Consulta>());
		when(mapper.toDTO(c)).thenReturn(cDTO);

		List<ConsultaDTO> saida = service.findConsultaByData(now);

		assertEquals(0,saida.size());

		verify(repo).findAllConsultaData(now);

		verifyNoMoreInteractions(repo);
	}
	
	@Test
	void cancelar() {
		c.setId(1L);
		c.setStatus("AGUARDANDO");

		when(repo.findById(anyLong())).thenReturn(Optional.of(c));
		when(mapper.toDTO(c)).thenReturn(cDTO);

		service.cancelar(1L);

		

		verify(repo).findById(1L);
		c.setStatus("CANCELADA");
		verify(repo).save(c);
		verifyNoMoreInteractions(repo);
	}
	
	
	@Test
	void realizar() {
		c.setId(3L);
		c.setStatus("AGUARDANDO");

		when(repo.findById(anyLong())).thenReturn(Optional.of(c));

		service.realizar(1L);

		

		Mockito.verify(repo).findById(1L);
		Mockito.verify(repo).save(c);
		Mockito.verifyNoMoreInteractions(repo);
	}
	
	@Test
	void findAllConsultaByPaciente() {
		c.setId(1L);
		p.setId(1L);
		cDTO.setId(1L);
		
		when(repo.findAllConsultaByPaciente(anyLong(),any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(c)));
		when(mapper.toDTO(c)).thenReturn(cDTO);

		Page<ConsultaDTO> saida = service.findAllConsultaByPaciente(p.getId(), PageRequest.of(0, 1));
		
		assertEquals(c.getId(),saida.getContent().get(0).getId());
		assertEquals(0,saida.getNumber());
	}

}
