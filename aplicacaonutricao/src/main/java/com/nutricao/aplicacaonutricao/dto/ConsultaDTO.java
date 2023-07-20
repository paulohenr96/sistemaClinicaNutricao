package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nutricao.aplicacaonutricao.model.Paciente;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {
	Long id;
	
	
	
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	LocalDateTime horario;
	
	PacienteDTO paciente;
	
	String status;
	
}
