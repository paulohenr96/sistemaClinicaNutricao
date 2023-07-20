package com.nutricao.aplicacaonutricao.mapper;

import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.dto.ConsultaDTO;
import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.model.Consulta;

@Component
public class ConsultaMapperImp implements Mapper<Consulta, ConsultaDTO> {

	@Override
	public ConsultaDTO toDTO(Consulta entity) {
		// TODO Auto-generated method stub
		ConsultaDTO dto=new ConsultaDTO();
		
		dto.setId(entity.getId());
		PacienteDTO paciente = new PacienteDTO();
		paciente.setId(entity.getPaciente().getId());
		paciente.setNome(entity.getPaciente().getNome());
		dto.setPaciente(paciente);
		dto.setHorario(entity.getHorario());
		dto.setStatus(entity.getStatus());
		
		return dto;
	}

	@Override
	public Consulta toEntity(ConsultaDTO dto) {
		// TODO Auto-generated method stub
		
		Consulta entity=new Consulta();
		if (dto.getId()!=null) {
			entity.setId(dto.getId());

		}
		
		entity.setHorario(dto.getHorario());
		if (dto.getStatus()!=null) {
			entity.setStatus(dto.getStatus());

		}
		
		return entity;
	}


}
