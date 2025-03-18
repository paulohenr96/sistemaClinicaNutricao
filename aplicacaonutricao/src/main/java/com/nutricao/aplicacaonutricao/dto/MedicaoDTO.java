package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nutricao.aplicacaonutricao.model.Paciente;


public class MedicaoDTO {
	Long id;
	
	private BigDecimal peso;
	
	private Integer altura;
	
	private BigDecimal imc;
	
	private BigDecimal percentualDeGordura;
	
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	
	private Paciente paciente;
	
	
	public MedicaoDTO(Long id, BigDecimal peso, Integer altura, BigDecimal imc, BigDecimal percentualDeGordura,
			LocalDate data) {
		this.id = id;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.percentualDeGordura = percentualDeGordura;
		this.data = data;
	}

	public MedicaoDTO(Long id, BigDecimal peso, Integer altura, BigDecimal imc, BigDecimal percentualDeGordura,
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

	public MedicaoDTO() {
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
