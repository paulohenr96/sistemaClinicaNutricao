package com.nutricao.aplicacaonutricao.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
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
public class Consulta {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	
	private LocalDateTime horario;
	
	@ManyToOne
	private Paciente paciente;
	
	private String status;

	public Consulta(Long id, LocalDateTime horario, Paciente paciente, String status) {
		super();
		this.id = id;
		this.horario = horario;
		this.paciente = paciente;
		this.status = status;
	}

	public Consulta() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
