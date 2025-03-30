package com.nutricao.aplicacaonutricao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutricao.aplicacaonutricao.model.Alimento;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long>{
	
	
//	@Query(value = "from Alimento a where a.nome like %:nome%",countQuery = "Select count(1) from Alimento a where a.nome like %:nome%")
	
	@Query(value = "FROM Alimento a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))",
       countQuery = "SELECT COUNT(a) FROM Alimento a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	Page<Alimento> findAllByNome(String nome,PageRequest pr);

}
