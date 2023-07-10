package com.nutricao.aplicacaonutricao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public void salvar(ConsultaDTO dto,Long paciente) {
		
		
		if (repository.verificaDisponibilidade(dto.getHorario())>0) {
			throw new RuntimeException("Já existe uma consulta neste horario");
		}
		
		Paciente p=new Paciente();
		p.setId(paciente);
		dto.setPaciente(p);
		Consulta Consulta = mapper.toEntity(dto);
		
		

		repository.save(Consulta);

	}

	public ConsultaDTO findProximaConsultaByPaciente(Long paciente) {
		
	List<Consulta> list = repository.findProximaConsultaByPaciente(paciente,LocalDateTime.now());
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



	
	

	
}
