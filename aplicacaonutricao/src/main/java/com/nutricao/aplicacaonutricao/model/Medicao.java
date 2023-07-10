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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
	
}
