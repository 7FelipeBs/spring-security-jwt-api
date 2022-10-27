package com.felipebs.seguranca.entidades;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class CargoAcesso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private CargoAcessoEnum name;

  public CargoAcesso() {

  }

  public CargoAcesso(CargoAcessoEnum name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CargoAcessoEnum getName() {
    return name;
  }

  public void setName(CargoAcessoEnum name) {
    this.name = name;
  }
}