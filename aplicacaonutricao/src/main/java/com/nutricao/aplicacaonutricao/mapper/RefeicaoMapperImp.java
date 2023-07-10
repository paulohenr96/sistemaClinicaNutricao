package com.nutricao.aplicacaonutricao.mapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.model.Refeicao;

@Component
public class RefeicaoMapperImp implements Mapper<Refeicao, RefeicaoDTO> {

	@Override
	public RefeicaoDTO toDTO(Refeicao entity) {
		// TODO Auto-generated method stub
		return new RefeicaoDTO(entity.getId(),entity.getNome(),entity.getHorario());
	}

	@Override
	public Refeicao toEntity(RefeicaoDTO dto) {
		// TODO Auto-generated method stub
		
		return new Refeicao(dto.getId(),dto.getNome(),dto.getHorario());
	}

}
