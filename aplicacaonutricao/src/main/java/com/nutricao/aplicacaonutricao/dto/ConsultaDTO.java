package com.nutricao.aplicacaonutricao.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
public class ConsultaDTO {
	Long id;
	
	
	

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	LocalDateTime horario;
	
	PacienteDTO paciente;
	
	String status;
	
	public ConsultaDTO(Long id, LocalDateTime horario, PacienteDTO paciente, String status) {
		super();
		this.id = id;
		this.horario = horario;
		this.paciente = paciente;
		this.status = status;
	}
	
	public ConsultaDTO() {
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

	public PacienteDTO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
