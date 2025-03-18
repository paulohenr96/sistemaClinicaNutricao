package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;
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
	public AlimentoRefeicaoDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRefeicao() {
		return refeicao;
	}
	public void setRefeicao(Long refeicao) {
		this.refeicao = refeicao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getAlimento() {
		return alimento;
	}
	public void setAlimento(Long alimento) {
		this.alimento = alimento;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
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
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlimentoRefeicaoDTO other = (AlimentoRefeicaoDTO) obj;
		return Objects.equals(id, other.id);
	}

	
}
