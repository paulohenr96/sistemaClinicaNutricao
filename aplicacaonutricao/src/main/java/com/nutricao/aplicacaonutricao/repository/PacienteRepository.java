package com.nutricao.aplicacaonutricao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutricao.aplicacaonutricao.model.Alimento;
import com.nutricao.aplicacaonutricao.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	
	
	@Query(value = "from Paciente a where a.nome like %:nome%",countQuery = "Select count(1) from Paciente a where a.nome like %:nome%")
	Page<Paciente> findAllByNome(String nome,PageRequest pr);
}
