package com.nutricao.aplicacaonutricao.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutricao.aplicacaonutricao.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	
	
	@Query("from Consulta c where  DATE(c.horario)=:data ORDER BY horario")
	List<Consulta> findAllConsultaData(LocalDate data);

	@Query("from Consulta c where c.paciente.id=:paciente and c.horario>:now ORDER BY horario")
	List<Consulta> findProximaConsultaByPaciente(Long paciente, LocalDateTime now);

	@Query("from Consulta c where c.paciente=:paciente and c.horario<:now ")
	Optional<Consulta> findConsultaRealizadaByPaciente(Long paciente, LocalDateTime now);
	

	@Query("SELECT COUNT(1) FROM Consulta c where c.horario=:horario ")
	Integer verificaDisponibilidade(LocalDateTime horario);
}