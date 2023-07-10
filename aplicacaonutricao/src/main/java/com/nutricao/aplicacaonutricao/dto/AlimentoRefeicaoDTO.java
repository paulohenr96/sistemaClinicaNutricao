package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class AlimentoRefeicaoDTO {

	private Long id;
	private Long refeicao;
	private String nome;
	private Long alimento;
	private BigDecimal quantidade;
	private BigDecimal proteina;
	private BigDecimal caloria;
	private BigDecimal carboidrato;
	private BigDecimal gordura;

	
}
