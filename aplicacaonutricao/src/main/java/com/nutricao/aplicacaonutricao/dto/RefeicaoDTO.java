package com.nutricao.aplicacaonutricao.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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


	public RefeicaoDTO() {
		super();
	}


	public RefeicaoDTO(Long id, String nome, LocalTime horario, BigDecimal caloria, BigDecimal proteina,
			BigDecimal carboidrato, BigDecimal gordura, List<AlimentoRefeicaoDTO> alimentos) {
		super();
		this.id = id;
		this.nome = nome;
		this.horario = horario;
		this.caloria = caloria;
		this.proteina = proteina;
		this.carboidrato = carboidrato;
		this.gordura = gordura;
		this.alimentos = alimentos;
	}


	private List<AlimentoRefeicaoDTO> alimentos;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalTime getHorario() {
		return horario;
	}


	public void setHorario(LocalTime horario) {
		this.horario = horario;
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


	public List<AlimentoRefeicaoDTO> getAlimentos() {
		return alimentos;
	}


	public void setAlimentos(List<AlimentoRefeicaoDTO> alimentos) {
		this.alimentos = alimentos;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RefeicaoDTO other = (RefeicaoDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
