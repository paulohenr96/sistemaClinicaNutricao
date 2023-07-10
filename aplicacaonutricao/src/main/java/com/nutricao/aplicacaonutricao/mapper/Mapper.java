package com.nutricao.aplicacaonutricao.mapper;

public interface Mapper<T,DTO> {

	DTO toDTO(T entity);
	
	T toEntity(DTO dto);
	
}
