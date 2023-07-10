package com.nutricao.aplicacaonutricao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutricao.aplicacaonutricao.model.Dieta;
import com.nutricao.aplicacaonutricao.model.Paciente;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long> {

	@Query("from Dieta d where d.paciente.id=:p")
	Dieta findByPaciente(Long p);

}
