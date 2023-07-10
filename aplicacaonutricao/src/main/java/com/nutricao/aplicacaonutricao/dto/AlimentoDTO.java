package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlimentoDTO {

	private Long id;
	private String nome;
	private BigDecimal porcao;
	private BigDecimal proteina;
	private BigDecimal caloria;
	private BigDecimal carboidrato;
	private BigDecimal gordura;

}
