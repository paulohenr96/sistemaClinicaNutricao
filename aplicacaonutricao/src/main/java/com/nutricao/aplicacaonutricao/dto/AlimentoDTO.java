package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AlimentoDTO {

	private Long id;
	private String nome;
	private BigDecimal porcao;
	private BigDecimal proteina;
	private BigDecimal caloria;
	private BigDecimal carboidrato;
	private BigDecimal gordura;
	
	
	
	
	
	public AlimentoDTO() {
		super();
	}
	public AlimentoDTO(Long id, String nome, BigDecimal porcao, BigDecimal proteina, BigDecimal caloria,
			BigDecimal carboidrato, BigDecimal gordura) {
		super();
		this.id = id;
		this.nome = nome;
		this.porcao = porcao;
		this.proteina = proteina;
		this.caloria = caloria;
		this.carboidrato = carboidrato;
		this.gordura = gordura;
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
	public BigDecimal getProteina() {
		return proteina;
	}
	public void setProteina(BigDecimal proteina) {
		this.proteina = proteina;
	}
	public BigDecimal getCaloria() {
		return caloria;
	}
	public void setCaloria(BigDecimal caloria) {
		this.caloria = caloria;
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
