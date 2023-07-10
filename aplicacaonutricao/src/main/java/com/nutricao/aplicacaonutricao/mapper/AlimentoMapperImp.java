package com.nutricao.aplicacaonutricao.mapper;

import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.dto.AlimentoDTO;
import com.nutricao.aplicacaonutricao.model.Alimento;

@Component
public class AlimentoMapperImp implements Mapper<Alimento,AlimentoDTO>{

	@Override
	public AlimentoDTO toDTO(Alimento entity) {
		AlimentoDTO dto=new AlimentoDTO();
		
		dto.setNome(entity.getNome());
		dto.setId(entity.getId());
		dto.setPorcao(entity.getPorcao());
		dto.setProteina(entity.getProteina());
		dto.setCarboidrato(entity.getCarboidrato());
		dto.setCaloria(entity.getCaloria());
		dto.setGordura(entity.getGordura());
		
		
		return dto;
	}

	@Override
	public Alimento toEntity(AlimentoDTO dto) {

		Alimento entity=new Alimento();
		entity.setNome(dto.getNome());
		entity.setId(dto.getId());
		entity.setPorcao(dto.getPorcao());
		entity.setProteina(dto.getProteina());
		entity.setCarboidrato(dto.getCarboidrato());
		entity.setCaloria(dto.getCaloria());
		entity.setGordura(dto.getGordura());
		
		
		
		return entity;
	}

}
