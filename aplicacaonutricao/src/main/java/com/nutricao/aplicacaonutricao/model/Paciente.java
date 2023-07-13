package com.nutricao.aplicacaonutricao.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String endereco;
	private String cpf;
	private String telefone;
	
    @Column(columnDefinition = "TEXT")
	private String foto;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeNascimento;
	
	@OneToMany
	private List<Medicao> medicoes;
	
	private String descricao;
	
	@OneToOne
	private Dieta dieta;

	public Paciente(Long id, String nome, String endereco, String cpf, String telefone, LocalDate dataDeNascimento,
			List<Medicao> medicoes, String descricao,String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
		this.medicoes = medicoes;
		this.descricao = descricao;
		this.foto=foto;
	}
	
	public Paciente() {
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
		Paciente other = (Paciente) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
