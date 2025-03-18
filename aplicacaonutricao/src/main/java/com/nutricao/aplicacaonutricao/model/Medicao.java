package com.nutricao.aplicacaonutricao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Medicao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(scale=2,precision=10)
	private BigDecimal peso;
	
	@Column(scale=2,precision=10)
	private Integer altura;
	
	@Column(scale=2,precision=10)
	private BigDecimal imc;
	
	@Column(scale=2,precision=10)
	private BigDecimal percentualDeGordura;
	
	@Temporal(TemporalType.DATE)
	private LocalDate data;
	
	@ManyToOne
	private Paciente paciente;

	public Medicao(Long id, BigDecimal peso, Integer altura, BigDecimal imc, BigDecimal percentualDeGordura,
			LocalDate data, Paciente paciente) {
		super();
		this.id = id;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.percentualDeGordura = percentualDeGordura;
		this.data = data;
		this.paciente = paciente;
	}

	public Medicao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public BigDecimal getImc() {
		return imc;
	}

	public void setImc(BigDecimal imc) {
		this.imc = imc;
	}

	public BigDecimal getPercentualDeGordura() {
		return percentualDeGordura;
	}

	public void setPercentualDeGordura(BigDecimal percentualDeGordura) {
		this.percentualDeGordura = percentualDeGordura;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	
}
