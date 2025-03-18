package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;
import java.util.List;

import com.nutricao.aplicacaonutricao.model.Paciente;
import com.nutricao.aplicacaonutricao.model.Refeicao;

import lombok.Data;
import lombok.NoArgsConstructor;

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



	public DietaDTO() {
		super();
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Paciente getPaciente() {
		return paciente;
	}



	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}



	public List<Refeicao> getRefeicoes() {
		return refeicoes;
	}



	public void setRefeicoes(List<Refeicao> refeicoes) {
		this.refeicoes = refeicoes;
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
