package com.felipebs.seguranca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebs.seguranca.entidades.Usuario;


@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
