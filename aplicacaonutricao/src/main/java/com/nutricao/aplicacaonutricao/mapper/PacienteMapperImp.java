package com.nutricao.aplicacaonutricao.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.model.Paciente;

@Component
public class PacienteMapperImp implements Mapper<Paciente, PacienteDTO> {

	@Override
	public PacienteDTO toDTO(Paciente entity) {
		// TODO Auto-generated method stub
		return new PacienteDTO(entity.getId(),entity.getNome(),
								entity.getEndereco(),entity.getCpf(),
								entity.getTelefone(),entity.getDataDeNascimento(),
								entity.getDescricao(),entity.getFoto());
	}

	@Override
	public Paciente toEntity(PacienteDTO dto) {
		// TODO Auto-generated method stub
		return new Paciente(dto.getId(),
				dto.getNome(),
				dto.getEndereco(),
				dto.getCpf(),
				dto.getTelefone(),
				dto.getDataDeNascimento(),
				null,
				dto.getDescricao(),dto.getFoto());
	}

}
