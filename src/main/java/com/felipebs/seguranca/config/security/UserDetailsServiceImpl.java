package com.felipebs.seguranca.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipebs.seguranca.entidades.Usuario;
import com.felipebs.seguranca.repository.IUsuarioRepositorio;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  IUsuarioRepositorio userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(usuario);
  }

}
