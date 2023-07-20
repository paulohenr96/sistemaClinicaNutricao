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
		Refeicao refeicao = mapper.toEntity(dto);
		Dieta dieta=dietaRepository.findByPaciente(paciente);
		
		if (dieta==null) {
			dieta = new Dieta();
			Paciente p = pacienteRepository.findById(paciente).get();
			relacionamentoDietaPaciente(dieta,p);
		} 
		
		
		relacionamentoRefeicaoDieta(refeicao,dieta);


	}

	private void relacionamentoRefeicaoDieta(Refeicao refeicao, Dieta dieta) {
		// TODO Auto-generated method stub
		refeicao.setDieta(dieta);
		Refeicao save = repository.save(refeicao);
		dieta.getRefeicoes().add(save);
		
		dietaRepository.save(dieta);
	}

	private void relacionamentoDietaPaciente(Dieta dieta, Paciente p) {
		// TODO Auto-generated method stub
		dieta.setPaciente(p);
		Dieta save = dietaRepository.save(dieta);
		p.setDieta(save);
		pacienteRepository.save(p);
	}

	public void addalimento(Long refeicao, AlimentoRefeicaoDTO ali) {

		Refeicao r = repository.findById(refeicao).get();

		AlimentoRefeicao ar = new AlimentoRefeicao();

		ar.setQuantidade(ali.getQuantidade());
		Long idAlimento = ali.getAlimento();
		Alimento a = alimentoRepository.findById(idAlimento).get();
		ar.setAlimento(a);
		relacionamentoRefeicaoAlimento(ar,r);

	}
	private void relacionamentoRefeicaoAlimento(AlimentoRefeicao ar,Refeicao r) {
		ar.setRefeicao(r);
		AlimentoRefeicao save = alimentoRefeicaoRepository.save(ar);
		r.getAlimentos().add(save);
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
