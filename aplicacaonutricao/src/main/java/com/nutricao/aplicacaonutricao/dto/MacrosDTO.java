package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;


public class MacrosDTO {

	
	private BigDecimal caloria;
	private BigDecimal proteina;
	private BigDecimal carboidrato;
	private BigDecimal gordura;
	
	
	
	
	public MacrosDTO(BigDecimal caloria, BigDecimal proteina, BigDecimal carboidrato, BigDecimal gordura) {
		super();
		this.caloria = caloria;
		this.proteina = proteina;
		this.carboidrato = carboidrato;
		this.gordura = gordura;
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
