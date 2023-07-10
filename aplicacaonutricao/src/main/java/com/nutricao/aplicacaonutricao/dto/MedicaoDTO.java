package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nutricao.aplicacaonutricao.model.Paciente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicaoDTO {
	Long id;
	
	private BigDecimal peso;
	
	private Integer altura;
	
	private BigDecimal imc;
	
	private BigDecimal percentualDeGordura;
	
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	public MedicaoDTO(Long id, BigDecimal peso, Integer altura, BigDecimal imc, BigDecimal percentualDeGordura,
			LocalDate data) {
		this.id = id;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.percentualDeGordura = percentualDeGordura;
		this.data = data;
	}

	private Paciente paciente;
}
