package com.nutricao.aplicacaonutricao.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UsuarioDTO {

	private Long id;
	private String nome;
	
	private String foto;
	private String endereco;
	private String telefone;
	public UsuarioDTO(Long id, String nome, String foto, String endereco, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.foto = foto;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	public UsuarioDTO() {
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
