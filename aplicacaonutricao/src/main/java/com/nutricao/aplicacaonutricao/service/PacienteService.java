package com.nutricao.aplicacaonutricao.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.exception.PacienteNotFoundException;
import com.nutricao.aplicacaonutricao.mapper.PacienteMapperImp;
import com.nutricao.aplicacaonutricao.mapper.PacienteMapperImp;
import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.repository.PacienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PacienteService {

	private final PacienteRepository repository;

	private final PacienteMapperImp mapper;

	public void salvar(PacienteDTO dto) {

		Paciente Paciente = mapper.toEntity(dto);

		repository.save(Paciente);

	}

	public PacienteDTO findPacienteById(Long id) {

		Optional<Paciente> optional = repository.findById(id);

		if (optional.isEmpty()) {
			throw new PacienteNotFoundException(id);
		}

		Paciente Paciente = optional.get();

		PacienteDTO dto = mapper.toDTO(Paciente);
		return dto;

	}

	public Page<PacienteDTO> findAllPaciente(PageRequest pr) {
		
		return repository.findAll(pr).map(mapper::toDTO);

	}

	public Page<PacienteDTO> findAllByNome(String nome, PageRequest pr) {

		return repository.findAllByNome(nome, pr).map(mapper::toDTO);
	}

	public void deletePaciente(Long id) {

		try {
			repository.deleteById(id);

		} catch (IllegalArgumentException e) {
			throw new PacienteNotFoundException(id);
		}

	}

	public HttpStatus updatePaciente(PacienteDTO dto) {
		Paciente entity = mapper.toEntity(dto);

		Optional<Paciente> optional = repository.findById(dto.getId());

		Paciente paciente = optional.get();
		paciente.setCpf(dto.getCpf());
		paciente.setNome(dto.getNome());
		paciente.setTelefone(dto.getTelefone());
		paciente.setEndereco(dto.getEndereco());
		paciente.setDescricao(dto.getDescricao());
		paciente.setDataDeNascimento(dto.getDataDeNascimento());

		repository.save(paciente);
		return HttpStatus.OK;
	}

}
