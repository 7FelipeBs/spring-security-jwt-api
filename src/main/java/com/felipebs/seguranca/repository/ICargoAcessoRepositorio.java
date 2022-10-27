package com.felipebs.seguranca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebs.seguranca.entidades.CargoAcessoEnum;
import com.felipebs.seguranca.entidades.CargoAcesso;


@Repository
public interface ICargoAcessoRepositorio extends JpaRepository<CargoAcesso, Long> {
  Optional<CargoAcesso> findByName(CargoAcessoEnum name);
}
