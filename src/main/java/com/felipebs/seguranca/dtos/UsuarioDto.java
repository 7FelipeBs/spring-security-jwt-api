package com.felipebs.seguranca.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
	
	private String email;
	
	private String nome;
	
	private List<String> listNomeCargos;

}
