package com.felipebs.seguranca.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.felipebs.seguranca.entidades.UsuarioDados;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<UsuarioDados, Long> {

	@Query("FROM UsuarioDados us"
			+ "	WHERE us.email = :email")
	UsuarioDados procurarEmail(String email);

	@Query("FROM UsuarioDados us "
			+ "	ORDER BY us.nome")
	Page<UsuarioDados> buscarTodosRegistros(Pageable paginacao);
}

