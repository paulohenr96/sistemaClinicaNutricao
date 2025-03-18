package com.nutricao.aplicacaonutricao.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Refeicao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	
    @Column(columnDefinition = "TIME")
	private LocalTime horario;
	
	@OneToMany(cascade =CascadeType.REMOVE,orphanRemoval = true)
	private List<AlimentoRefeicao> alimentos=new ArrayList<>();
	
	@ManyToOne
	private Dieta dieta;

	public Refeicao(Long id, String nome, LocalTime horario) {
		this.id = id;
		this.nome = nome;
		this.horario = horario;
	}

	public Refeicao() {
		super();
	}

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

	public List<AlimentoRefeicao> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<AlimentoRefeicao> alimentos) {
		this.alimentos = alimentos;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}
	
	
}
