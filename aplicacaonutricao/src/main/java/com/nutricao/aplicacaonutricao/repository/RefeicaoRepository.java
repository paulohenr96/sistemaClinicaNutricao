package com.nutricao.aplicacaonutricao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutricao.aplicacaonutricao.model.Refeicao;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
	
	
	@Query("from Refeicao r where r.dieta.id=:dieta")
	List<Refeicao> findAllByDieta(Long dieta);
}
