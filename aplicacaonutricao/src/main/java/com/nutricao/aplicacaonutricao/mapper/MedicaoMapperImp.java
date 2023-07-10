package com.nutricao.aplicacaonutricao.mapper;

import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.dto.MedicaoDTO;
import com.nutricao.aplicacaonutricao.model.Medicao;

@Component
public class MedicaoMapperImp implements Mapper<Medicao, MedicaoDTO> {

	@Override
	public MedicaoDTO toDTO(Medicao entity) {
		// TODO Auto-generated method stub
		return new MedicaoDTO(entity.getId(),entity.getPeso(),entity.getAltura(),entity.getImc(),entity.getPercentualDeGordura(),entity.getData());
	}

	@Override
	public Medicao toEntity(MedicaoDTO dto) {
		// TODO Auto-generated method stub
		return new Medicao(dto.getId(),dto.getPeso(),dto.getAltura(),dto.getImc(),dto.getPercentualDeGordura(),dto.getData(),dto.getPaciente());
	}

}
