package com.nutricao.aplicacaonutricao.model;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class AlimentoRefeicao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(scale = 2,precision=10)
	private BigDecimal quantidade;
	
	
	@ManyToOne
	private Alimento alimento;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Refeicao refeicao;

	public AlimentoRefeicao(Long id, BigDecimal quantidade, Alimento alimento, Refeicao refeicao) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.alimento = alimento;
		this.refeicao = refeicao;
	}

	public AlimentoRefeicao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}
	
	
	
}
