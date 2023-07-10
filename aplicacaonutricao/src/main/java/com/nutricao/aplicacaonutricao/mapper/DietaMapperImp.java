package com.nutricao.aplicacaonutricao.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.dto.DietaDTO;
import com.nutricao.aplicacaonutricao.model.Dieta;

@Component
public class DietaMapperImp implements Mapper<Dieta, DietaDTO> {

	@Override
	public DietaDTO toDTO(Dieta entity) {
		// TODO Auto-generated method stub
		return new DietaDTO(entity.getId(),entity.getRefeicoes());
	}

	@Override
	public Dieta toEntity(DietaDTO dto) {
		// TODO Auto-generated method stub
		return new Dieta(dto.getId(),null,dto.getRefeicoes());
	}

}
