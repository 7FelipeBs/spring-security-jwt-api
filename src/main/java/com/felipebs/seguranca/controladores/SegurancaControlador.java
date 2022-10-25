package com.felipebs.seguranca.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipebs.seguranca.dtos.UsuarioDto;
import com.felipebs.seguranca.servicos.UsuarioServico;

@RestController
@RequestMapping("/dadosAcesso")
public class SegurancaControlador {
	
	@Autowired
	private UsuarioServico usuarioServico; 
	
	@GetMapping()
	public List<UsuarioDto> buscarTodosUsuario (
			@RequestParam(name = "pagina") Integer pagina,
			@RequestParam(name = "quantidadeRegistro") Integer quantidadeRegistro) {
		// felipe@gmail.com / felipe123 - acesso
		// raquel@gmail.com / raquel123 - acesso
		return usuarioServico.buscarTodosUsuario(pagina, quantidadeRegistro);
	}
}
