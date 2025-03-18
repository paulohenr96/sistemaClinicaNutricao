package com.nutricao.aplicacaonutricao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nutricao.aplicacaonutricao.dto.AlimentoDTO;
import com.nutricao.aplicacaonutricao.exception.AlimentoNotFoundException;
import com.nutricao.aplicacaonutricao.mapper.AlimentoMapperImp;
import com.nutricao.aplicacaonutricao.model.Alimento;
import com.nutricao.aplicacaonutricao.repository.AlimentoRepository;

import lombok.AllArgsConstructor;

@Service
public class AlimentoService {

	private final AlimentoRepository repository;

	private final AlimentoMapperImp mapper;

	public AlimentoService(AlimentoRepository repository, AlimentoMapperImp mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}

	public void salvar(AlimentoDTO dto) {

		Alimento alimento = mapper.toEntity(dto);

		repository.save(alimento);

	}

	public AlimentoDTO findAlimentoById(Long id) {

		Optional<Alimento> optional = repository.findById(id);

		if (optional.isEmpty()) {
			throw new AlimentoNotFoundException(id);
		}

		Alimento alimento = optional.get();

		AlimentoDTO dto = mapper.toDTO(alimento);

		return dto;

	}

	public Page<AlimentoDTO> findAllAlimento(PageRequest pr) {

		return repository.findAll(pr).map(mapper::toDTO);

	}

	public Page<AlimentoDTO> findAllByNome(String nome, PageRequest pr) {

		return repository.findAllByNome(nome, pr).map(mapper::toDTO);
	}

	public void deleteAlimento(Long id) {

		try {
			repository.deleteById(id);

		} catch (IllegalArgumentException e) {
			throw new AlimentoNotFoundException(id);
		}

	}

	public HttpStatus updateAlimento(AlimentoDTO dto) {
		Alimento entity = mapper.toEntity(dto);

		Optional<Alimento> optional = repository.findById(dto.getId());

		if (optional.isEmpty()) {
			repository.save(entity);
			return HttpStatus.CREATED;
		}
		repository.save(entity);
		return HttpStatus.OK;
	}

}
