package com.nutricao.aplicacaonutricao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutricao.aplicacaonutricao.model.AlimentoRefeicao;

@Repository
public interface AlimentoRefeicaoRepository extends JpaRepository<AlimentoRefeicao, Long>{
	
	

}
