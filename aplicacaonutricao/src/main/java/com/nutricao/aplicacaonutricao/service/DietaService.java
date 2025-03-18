package com.nutricao.aplicacaonutricao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nutricao.aplicacaonutricao.calculo.CalcularMacros;
import com.nutricao.aplicacaonutricao.dto.DietaDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.exception.DietaNotFoundException;
import com.nutricao.aplicacaonutricao.mapper.DietaMapperImp;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.mapper.RefeicaoMapperImp;
import com.nutricao.aplicacaonutricao.model.Dieta;
import com.nutricao.aplicacaonutricao.model.Refeicao;
import com.nutricao.aplicacaonutricao.repository.DietaRepository;
import com.nutricao.aplicacaonutricao.repository.PacienteRepository;
import com.nutricao.aplicacaonutricao.repository.RefeicaoRepository;

import lombok.AllArgsConstructor;

@Service
public class DietaService {

	private final DietaRepository repository;
	private final PacienteRepository pacienteRepository;
	private final RefeicaoRepository refeicaoRepository;

	private final DietaMapperImp mapper;
	private final RefeicaoMapperImp refeicaoMapper;

	public DietaService(DietaRepository repository, PacienteRepository pacienteRepository,
			RefeicaoRepository refeicaoRepository, DietaMapperImp mapper, RefeicaoMapperImp refeicaoMapper) {
		super();
		this.repository = repository;
		this.pacienteRepository = pacienteRepository;
		this.refeicaoRepository = refeicaoRepository;
		this.mapper = mapper;
		this.refeicaoMapper = refeicaoMapper;
	}

	public void salvar(DietaDTO dto) {

		Dieta Dieta = mapper.toEntity(dto);

		repository.save(Dieta);

	}

	public DietaDTO findDietaByPaciente(Long paciente) {

		Dieta dieta = repository.findByPaciente(paciente);
		if (dieta == null) {
			return null;
		}
		DietaDTO dto = mapper.toDTO(dieta);

		return dto;

	}

	public void deleteDieta(Long id) {

		try {
			repository.deleteById(id);

		} catch (IllegalArgumentException e) {
			throw new DietaNotFoundException(id);
		}

	}

	public String novaRefeicao(RefeicaoDTO refeicaoDTO, Long paciente) {
		// TODO Auto-generated method stub
		Dieta dieta = repository.findByPaciente(paciente);
		Refeicao entity = refeicaoMapper.toEntity(refeicaoDTO);

		if (dieta == null) {
			dieta = new Dieta();
			dieta.setPaciente(pacienteRepository.findById(paciente).get());
			repository.save(dieta);


		} 
		dieta.getRefeicoes().add(entity);

		entity.setDieta(dieta);
		refeicaoRepository.save(entity);
		repository.save(dieta);

		return JsonMapper
				.mapearJson(dieta.getRefeicoes()
						.stream()
						.map(CalcularMacros::refeicao)
						.collect(Collectors.toList()));

	}

	public String todasAsRefeicoesEmJson(Long paciente) {

		Dieta d = repository.findByPaciente(paciente);
		if (d == null) {
			return JsonMapper.mapearJson(new ArrayList<>());
		}
		return JsonMapper
				.mapearJson(d.getRefeicoes().stream().map(CalcularMacros::refeicao).collect(Collectors.toList()));
	}

	public String findRefeicaoById(Long refeicao) {

		Refeicao entity = refeicaoRepository.findById(refeicao).get();
		RefeicaoDTO refeicaoDto = refeicaoMapper.toDTO(entity);

		return JsonMapper.mapearJson(refeicaoDto);

	}

}
