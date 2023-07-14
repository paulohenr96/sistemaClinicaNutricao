package com.nutricao.aplicacaonutricao.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nutricao.aplicacaonutricao.calculo.CalcularMacros;
import com.nutricao.aplicacaonutricao.dto.AlimentoRefeicaoDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.RefeicaoMapperImp;
import com.nutricao.aplicacaonutricao.model.Alimento;
import com.nutricao.aplicacaonutricao.model.AlimentoRefeicao;
import com.nutricao.aplicacaonutricao.model.Dieta;
import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.model.Refeicao;
import com.nutricao.aplicacaonutricao.repository.AlimentoRefeicaoRepository;
import com.nutricao.aplicacaonutricao.repository.AlimentoRepository;
import com.nutricao.aplicacaonutricao.repository.DietaRepository;
import com.nutricao.aplicacaonutricao.repository.PacienteRepository;
import com.nutricao.aplicacaonutricao.repository.RefeicaoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RefeicaoService {

	private final RefeicaoRepository repository;
	private final DietaRepository dietaRepository;
	private final AlimentoRefeicaoRepository alimentoRefeicaoRepository;
	private final AlimentoRepository alimentoRepository;
	private final PacienteRepository pacienteRepository;

	private final RefeicaoMapperImp mapper;

	public void salvar(Long paciente, RefeicaoDTO dto) {

		Dieta dieta=dietaRepository.findByPaciente(paciente);
		if (dieta==null) {
			dieta = new Dieta();
			dieta.setPaciente(pacienteRepository.findById(paciente).get());
			dietaRepository.save(dieta);


		} 
		

		Refeicao refeicao = mapper.toEntity(dto);
		
		dieta.getRefeicoes().add(refeicao);
		refeicao.setDieta(dieta);
		
		repository.save(refeicao);
		dietaRepository.save(dieta);


	}

	public void addalimento(Long refeicao, AlimentoRefeicaoDTO ali) {

		Refeicao r = repository.findById(refeicao).get();

		AlimentoRefeicao ar = new AlimentoRefeicao();

		ar.setQuantidade(ali.getQuantidade());
		Long idAlimento = ali.getAlimento();
		Alimento a = alimentoRepository.findById(idAlimento).get();

		ar.setAlimento(a);
		ar.setRefeicao(r);
		r.getAlimentos().add(ar);

		alimentoRefeicaoRepository.save(ar);
		repository.save(r);

	}

	public List<RefeicaoDTO> findAllByDieta(Long dieta) {

		return repository.findAllByDieta(dieta).stream().map(mapper::toDTO).collect(Collectors.toList());
	}

	public RefeicaoDTO findById(Long id) {
		Refeicao refeicao = repository.findById(id).get();
		RefeicaoDTO dto = mapper.toDTO(refeicao);

		List<AlimentoRefeicaoDTO> collect = refeicao.getAlimentos().stream().map(CalcularMacros::alimento)
				.collect(Collectors.toList());
		dto.setAlimentos(collect);
		return dto;
	}

	public void deleteRefeicao(Long id) {

		Refeicao refeicao = repository.findById(id).get();
		Dieta dieta = refeicao.getDieta();
		
		dieta.getRefeicoes().remove(refeicao);
		
		
		dietaRepository.save(dieta);
		repository.deleteById(id);

	}

	public void removerAlimento(Long alimento) {
		// TODO Auto-generated method stub
		AlimentoRefeicao alimentoRefeicao = alimentoRefeicaoRepository.findById(alimento).get();
		Refeicao refeicao = alimentoRefeicao.getRefeicao();
		refeicao.getAlimentos().remove(alimentoRefeicao);
		repository.save(refeicao);
	}

}
