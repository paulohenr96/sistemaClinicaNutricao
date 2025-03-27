package com.nutricao.aplicacaonutricao.beans;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.nutricao.aplicacaonutricao.model.Usuario;
import com.nutricao.aplicacaonutricao.repository.AlimentoRepository;
import com.nutricao.aplicacaonutricao.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@Component
public class InitializerBean {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AlimentoRepository alimentoRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void createData() {
		Optional<Usuario> userAdminOptional = usuarioRepository.findByUsername("admin");
		if (userAdminOptional.isEmpty()) {
			Usuario user = new Usuario();

			user.setUsername("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setFoto("/img/semperfil-foto.png");
			usuarioRepository.save(user);

		} else {

			Usuario user = userAdminOptional.get();

			if (user.getFoto() == null) {
				user.setFoto("/img/semperfil-foto.png");
				usuarioRepository.save(user);
			}

		}
		boolean existeAlimento = alimentoRepository.existsById(1L);
		if (!existeAlimento) {
			String sql = """
					    INSERT INTO public.alimento(id, caloria, carboidrato, gordura, nome, porcao, proteina) VALUES
					    (1, 89, 23.0, 0.3, 'Banana', 100, 1.1),
					    (2, 165, 0.0, 3.6, 'Peito de Frango Grelhado', 100, 31.0),
					    (3, 52, 14.0, 0.2, 'Maçã', 100, 0.3),
					    (4, 130, 28.0, 0.3, 'Arroz Branco Cozido', 100, 2.7),
					    (5, 77, 17.0, 0.1, 'Batata Cozida', 100, 2.0),
					    (6, 240, 9.0, 20.0, 'Abacate', 100, 2.0),
					    (7, 118, 3.9, 10.0, 'Ovo Cozido', 100, 13.0),
					    (8, 43, 10.0, 0.1, 'Cenoura Crua', 100, 1.0),
					    (9, 61, 1.0, 4.5, 'Iogurte Natural', 100, 3.5),
					    (10, 85, 4.5, 7.2, 'Queijo Minas Frescal', 50, 5.0);
					""";

			jdbcTemplate.update(sql);
		}
	}

}
