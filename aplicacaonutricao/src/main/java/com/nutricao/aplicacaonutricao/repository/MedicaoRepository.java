package com.nutricao.aplicacaonutricao.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutricao.aplicacaonutricao.model.Medicao;

@Repository
public interface MedicaoRepository extends JpaRepository<Medicao, Long> {

	
	@Query(value = "from Medicao m where m.paciente.id=:paciente",countQuery = "select count(1) from Medicao m where m.paciente.id=:paciente")
	Page<Medicao> findAllByPaciente(Long paciente, PageRequest page);

	
	@Query("select count(*) from Medicao m where m.data=:data AND m.paciente.id=:paciente")
	int existByData(LocalDate data,Long paciente);


}
