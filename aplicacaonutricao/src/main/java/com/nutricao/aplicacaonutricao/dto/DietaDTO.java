package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;
import java.util.List;

import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.model.Refeicao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DietaDTO {

	private Long id;
	
	private Paciente paciente;
	
	private List<Refeicao> refeicoes;
	
	private BigDecimal caloria;
	private BigDecimal proteina;
	private BigDecimal carboidrato;
	private BigDecimal gordura;

	
	
	public DietaDTO(Long id,List<Refeicao> refeicoes) {
		
		this.id=id;
		this.refeicoes=refeicoes;
		
	}
}
