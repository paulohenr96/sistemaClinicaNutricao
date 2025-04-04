package com.nutricao.aplicacaonutricao.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nutricao.aplicacaonutricao.calculo.CalcularMedidas;
import com.nutricao.aplicacaonutricao.dto.MedicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.MedicaoMapperImp;
import com.nutricao.aplicacaonutricao.model.Medicao;
import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.repository.MedicaoRepository;
import com.nutricao.aplicacaonutricao.repository.PacienteRepository;

@Service
public class MedicaoService {

	private final MedicaoRepository repository;
	private final PacienteRepository pacienteRepository;

	private final MedicaoMapperImp mapper;

	public MedicaoService(MedicaoRepository repository, PacienteRepository pacienteRepository,
			MedicaoMapperImp mapper) {
		super();
		this.repository = repository;
		this.pacienteRepository = pacienteRepository;
		this.mapper = mapper;
	}


	public void salvar(MedicaoDTO dto,Long paciente) {

		
		int valor = repository.existByData(dto.getData(),paciente);
		if (valor>0) {
			throw new RuntimeException("Você já cadastrou uma medição nesta data.");
		}
		Medicao medicao = mapper.toEntity(dto);
		Integer altura = medicao.getAltura();
		BigDecimal peso = medicao.getPeso();
		medicao.setImc(CalcularMedidas.imc(altura, peso));
		
		
		Paciente p = pacienteRepository.findById(paciente).get();
		relacionamento(p,medicao);

		

	}

	
	private void relacionamento(Paciente p, Medicao medicao) {
		medicao.setPaciente(p);
		Medicao save = repository.save(medicao);
		p.getMedicoes().add(save);
		pacienteRepository.save(p);		
	}


	public Page<MedicaoDTO> findAllMedicaoPaginado(Long paciente,PageRequest page){
		return repository.findAllByPaciente(paciente,page).map(mapper::toDTO);
	}
	
	public Page<MedicaoDTO> findAllMedicaoOrdenada(Long paciente,String conteudo){
		Sort sort = Sort.by(Sort.Direction.ASC, conteudo);
		Page<MedicaoDTO> retorno = 
				repository.findAllByPaciente(paciente,PageRequest.of(0, Integer.MAX_VALUE,sort)).map(mapper::toDTO);
		return retorno;
	}
	public void deleteMedicaoByID(Long id) {
			repository.deleteById(id);
	}


	

	



}
