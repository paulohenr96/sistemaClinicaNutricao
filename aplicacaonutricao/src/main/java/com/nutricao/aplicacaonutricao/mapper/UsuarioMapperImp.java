package com.nutricao.aplicacaonutricao.mapper;

import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.dto.UsuarioDTO;
import com.nutricao.aplicacaonutricao.model.Usuario;

@Component
public class UsuarioMapperImp implements Mapper<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO toDTO(Usuario entity) {
		// TODO Auto-generated method stub
		return new UsuarioDTO(entity.getId(),entity.getNome(),entity.getFoto(),entity.getEndereco(),entity.getTelefone());
	}

	@Override
	public Usuario toEntity(UsuarioDTO dto) {
		// TODO Auto-generated method stub
		return new Usuario(dto.getId(),dto.getNome(),dto.getFoto(),dto.getEndereco(),dto.getTelefone());
	}

}
