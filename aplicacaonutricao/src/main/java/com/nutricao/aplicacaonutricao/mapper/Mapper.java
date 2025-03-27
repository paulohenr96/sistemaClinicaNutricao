package com.nutricao.aplicacaonutricao.mapper;

import java.io.IOException;

public interface Mapper<T,DTO> {

	DTO toDTO(T entity);
	
	T toEntity(DTO dto);
	
}
