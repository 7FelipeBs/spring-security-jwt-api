package com.felipebs.seguranca.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.felipebs.seguranca.dtos.UsuarioDto;
import com.felipebs.seguranca.entidades.CargoAcesso;
import com.felipebs.seguranca.entidades.UsuarioDados;
import com.felipebs.seguranca.repositorios.IUsuarioRepositorio;

@Service
public class UsuarioServico implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	IUsuarioRepositorio usuarioRepositorio;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioDados usuarioDados = usuarioRepositorio.procurarEmail(email);
		
		if(usuarioDados == null) throw new UsernameNotFoundException("Não foi possivel encontrar esse usuario com o email: " + email);
		return new User(usuarioDados.getUsername(), usuarioDados.getPassword(), true, true, true, true, usuarioDados.getAuthorities());
	}

	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<UsuarioDto> buscarTodosUsuario(Integer pagina, Integer quantidadeRegistro) {
		
		List<UsuarioDto> usuarios = new ArrayList<>();
		
		for (UsuarioDados usuario : usuarioRepositorio.buscarTodosRegistros(PageRequest.of(pagina,  quantidadeRegistro)).getContent()) {
			UsuarioDto dto = new UsuarioDto();
			
			dto.setNome(usuario.getNome());
			dto.setEmail(usuario.getEmail());
			
			List<String> listNomeCargos = new ArrayList<>();
			for (CargoAcesso item : usuario.getCargosAcesso()) {
				listNomeCargos.add(item.getAuthority());
			}
			dto.setListNomeCargos(listNomeCargos);
			
			usuarios.add(dto);
		}
        return usuarios;
	}


}
