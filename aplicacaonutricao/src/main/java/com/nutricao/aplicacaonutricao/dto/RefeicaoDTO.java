package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefeicaoDTO {
	private Long id;
	private String nome;
	
	
    @JsonFormat(pattern = "HH:mm")
	private LocalTime horario;
	
    private BigDecimal caloria;   
    private BigDecimal proteina;   
    private BigDecimal carboidrato;   
    private BigDecimal gordura;   

    public RefeicaoDTO(Long id, String nome, LocalTime horario) {
		super();
		this.id = id;
		this.nome = nome;
		this.horario = horario;
	}


	private List<AlimentoRefeicaoDTO> alimentos;
}
