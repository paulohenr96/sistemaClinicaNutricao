package com.nutricao.aplicacaonutricao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nutricao.aplicacaonutricao.dto.ConsultaDTO;
import com.nutricao.aplicacaonutricao.mapper.ConsultaMapperImp;
import com.nutricao.aplicacaonutricao.model.Consulta;
import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.repository.ConsultaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsultaService {

	private final ConsultaRepository repository;

	private final ConsultaMapperImp mapper;

	public void salvar(ConsultaDTO dto, Long paciente) {

		if (repository.verificaDisponibilidade(dto.getHorario()) > 0) {
			throw new RuntimeException("Já existe uma consulta neste horario");
		}

		Paciente p = new Paciente();
		p.setId(paciente);
		Consulta consulta = mapper.toEntity(dto);
		consulta.setPaciente(p);
		consulta.setStatus("AGUARDANDO");

		repository.save(consulta);

	}

	public ConsultaDTO findProximaConsultaByPaciente(Long paciente) {

		int monthValue = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		int dayOfMonth = LocalDateTime.now().getDayOfMonth();

		LocalDateTime hoje = LocalDateTime.of(year, monthValue, dayOfMonth, 0, 0);
		List<Consulta> list = repository.findProximaConsultaByPaciente(paciente, hoje);
		if (list.isEmpty()) {
			return null;
		}
		ConsultaDTO dto = mapper.toDTO(list.get(0));

		return dto;

	}

	public List<ConsultaDTO> findConsultaByData(LocalDate data) {

		List<Consulta> list = repository.findAllConsultaData(data);

		return list.stream().map(mapper::toDTO).collect(Collectors.toList());

	}
	public List<ConsultaDTO> findConsultaAgendadaByData(LocalDate data) {

		List<Consulta> list = repository.findAllConsultaAgendadaData(data);

		return list.stream().map(mapper::toDTO).collect(Collectors.toList());

	}

	public void cancelar(Long consulta) {
		// TODO Auto-generated method stub
		Consulta c = repository.findById(consulta).get();

		c.setStatus("CANCELADA");
		repository.save(c);
	}

	public void realizar(Long consulta) {
		// TODO Auto-generated method stub
		Consulta c = repository.findById(consulta).get();

		c.setStatus("REALIZADA");
		repository.save(c);
	}

	public Page<ConsultaDTO> findAllConsultaByPaciente(Long paciente, PageRequest of) {

		return repository.findAllConsultaByPaciente(paciente, of).map(mapper::toDTO);
	}

}
