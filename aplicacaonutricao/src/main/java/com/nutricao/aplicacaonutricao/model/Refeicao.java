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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Refeicao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	
    @Column(columnDefinition = "TIME")
	private LocalTime horario;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<AlimentoRefeicao> alimentos=new ArrayList<>();
	
	@ManyToOne
	private Dieta dieta;

	public Refeicao(Long id, String nome, LocalTime horario) {
		this.id = id;
		this.nome = nome;
		this.horario = horario;
	}
	
	
}
