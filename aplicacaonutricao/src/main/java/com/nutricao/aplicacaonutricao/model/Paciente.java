package com.nutricao.aplicacaonutricao.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
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
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Medicao> medicoes;
	
	private String descricao;
	
	@OneToOne(cascade = CascadeType.REMOVE,orphanRemoval = true)
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
	
	
	public Paciente(Long id, String nome, String endereco, String cpf, String telefone, String foto,
			LocalDate dataDeNascimento, List<Medicao> medicoes, String descricao, Dieta dieta) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.telefone = telefone;
		this.foto = foto;
		this.dataDeNascimento = dataDeNascimento;
		this.medicoes = medicoes;
		this.descricao = descricao;
		this.dieta = dieta;
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


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}


	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}


	public List<Medicao> getMedicoes() {
		return medicoes;
	}


	public void setMedicoes(List<Medicao> medicoes) {
		this.medicoes = medicoes;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Dieta getDieta() {
		return dieta;
	}


	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}
	
	

}
