package com.nutricao.aplicacaonutricao.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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

	public Alimento(Long id, String nome, BigDecimal porcao, BigDecimal caloria, BigDecimal proteina,
			BigDecimal carboidrato, BigDecimal gordura) {
		super();
		this.id = id;
		this.nome = nome;
		this.porcao = porcao;
		this.caloria = caloria;
		this.proteina = proteina;
		this.carboidrato = carboidrato;
		this.gordura = gordura;
	}

	public Alimento() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPorcao() {
		return porcao;
	}

	public void setPorcao(BigDecimal porcao) {
		this.porcao = porcao;
	}

	public BigDecimal getCaloria() {
		return caloria;
	}

	public void setCaloria(BigDecimal caloria) {
		this.caloria = caloria;
	}

	public BigDecimal getProteina() {
		return proteina;
	}

	public void setProteina(BigDecimal proteina) {
		this.proteina = proteina;
	}

	public BigDecimal getCarboidrato() {
		return carboidrato;
	}

	public void setCarboidrato(BigDecimal carboidrato) {
		this.carboidrato = carboidrato;
	}

	public BigDecimal getGordura() {
		return gordura;
	}

	public void setGordura(BigDecimal gordura) {
		this.gordura = gordura;
	}

	
}
