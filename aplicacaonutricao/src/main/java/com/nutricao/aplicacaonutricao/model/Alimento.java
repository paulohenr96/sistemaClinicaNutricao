package com.nutricao.aplicacaonutricao.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Alimento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String nome;
	
	@Column(scale=2,precision = 10)
	private BigDecimal porcao;
	
	@Column(scale=2,precision = 10)
	private BigDecimal caloria;

	@Column(scale=2,precision = 10)
	private BigDecimal proteina;

	@Column(scale=2,precision = 10)
	private BigDecimal carboidrato;

	@Column(scale=2,precision = 10)
	private BigDecimal gordura;

	
}
