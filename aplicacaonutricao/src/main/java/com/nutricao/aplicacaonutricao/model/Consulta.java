package com.nutricao.aplicacaonutricao.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
	
	@Id
	@GeneratedValue
	Long id;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	
	LocalDateTime horario;
	
	@ManyToOne
	Paciente paciente;
	
	String status;
	
	

}