package com.nutricao.aplicacaonutricao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	
	
	private String username;
	private String password;
	private String nome;
	
	
	private String endereco;
	private String telefone;
	
	
	@Column(columnDefinition = "TEXT")
	private String foto;

	public Usuario(Long id, String nome, String foto,String endereco,String telefone) {
		this.id = id;
		this.nome = nome;
		this.foto = foto;
		this.endereco = endereco;
		this.telefone = telefone;

	}
	
	
	
}
