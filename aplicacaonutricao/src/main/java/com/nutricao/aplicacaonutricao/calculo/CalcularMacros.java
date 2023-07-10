package com.nutricao.aplicacaonutricao.calculo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import com.nutricao.aplicacaonutricao.dto.AlimentoRefeicaoDTO;
import com.nutricao.aplicacaonutricao.dto.DietaDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.model.Alimento;
import com.nutricao.aplicacaonutricao.model.AlimentoRefeicao;
import com.nutricao.aplicacaonutricao.model.Dieta;
import com.nutricao.aplicacaonutricao.model.Refeicao;

public class CalcularMacros {

	
	
	
	public static AlimentoRefeicaoDTO alimento(AlimentoRefeicao ar) {
		Alimento alimento = ar.getAlimento();
		BigDecimal quantidade = ar.getQuantidade();
		AlimentoRefeicaoDTO ali=new AlimentoRefeicaoDTO();
		
		ali.setId(ar.getId());
		ali.setNome(ar.getAlimento().getNome());
		
		BigDecimal caloria = alimento.getCaloria().multiply(quantidade.divide(alimento.getPorcao())); 
		BigDecimal carboidrato = alimento.getCarboidrato().multiply(quantidade.divide(alimento.getPorcao())); 
		BigDecimal gordura = alimento.getGordura().multiply(quantidade.divide(alimento.getPorcao())); 
		BigDecimal proteina = alimento.getProteina().multiply(quantidade.divide(alimento.getPorcao())); 
		
		ali.setCaloria(caloria.setScale(2,RoundingMode.UP));
		ali.setCarboidrato(carboidrato.setScale(2,RoundingMode.UP));
		ali.setProteina(proteina.setScale(2,RoundingMode.UP));
		ali.setGordura(gordura.setScale(2,RoundingMode.UP));
		ali.setQuantidade(quantidade.setScale(2,RoundingMode.UP));
		
		
		return ali;
		
		
	}
	
	public static RefeicaoDTO refeicao(Refeicao ref) {
		RefeicaoDTO refeicao = new RefeicaoDTO();
		refeicao.setId(ref.getId());
		refeicao.setNome(ref.getNome());
		refeicao.setHorario(ref.getHorario());
		
		List<AlimentoRefeicaoDTO> collect = ref.getAlimentos().stream().map(e->alimento(e)).collect(Collectors.toList());
		
		refeicao.setProteina(collect.stream().map(AlimentoRefeicaoDTO::getProteina).reduce(BigDecimal.ZERO,BigDecimal::add));
		refeicao.setCaloria(collect.stream().map(AlimentoRefeicaoDTO::getCaloria).reduce(BigDecimal.ZERO,BigDecimal::add));
		refeicao.setCarboidrato(collect.stream().map(AlimentoRefeicaoDTO::getCarboidrato).reduce(BigDecimal.ZERO,BigDecimal::add));
		refeicao.setGordura(collect.stream().map(AlimentoRefeicaoDTO::getGordura).reduce(BigDecimal.ZERO,BigDecimal::add));

		return refeicao;
	}
	
	public static DietaDTO dieta(Dieta d) {
		DietaDTO dieta = new DietaDTO();
		dieta.setId(d.getId());
		
		List<RefeicaoDTO> collect = d.getRefeicoes().stream().map(e->refeicao(e)).collect(Collectors.toList());
		
		dieta.setProteina(collect.stream().map(RefeicaoDTO::getProteina).reduce(BigDecimal.ZERO,BigDecimal::add));
		dieta.setCaloria(collect.stream().map(RefeicaoDTO::getCaloria).reduce(BigDecimal.ZERO,BigDecimal::add));
		dieta.setCarboidrato(collect.stream().map(RefeicaoDTO::getCarboidrato).reduce(BigDecimal.ZERO,BigDecimal::add));
		dieta.setGordura(collect.stream().map(RefeicaoDTO::getGordura).reduce(BigDecimal.ZERO,BigDecimal::add));

		return dieta;
	}
}
