package com.nutricao.aplicacaonutricao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutricao.aplicacaonutricao.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
	
	@Query("from Usuario u where u.username=:username")
	Optional<Usuario> findByUsername(String username);
}
