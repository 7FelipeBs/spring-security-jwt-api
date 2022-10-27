package com.felipebs.seguranca.controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipebs.seguranca.entidades.CargoAcessoEnum;
import com.felipebs.seguranca.config.jwt.JwtUtils;
import com.felipebs.seguranca.config.security.UserDetailsImpl;
import com.felipebs.seguranca.entidades.CargoAcesso;
import com.felipebs.seguranca.entidades.Usuario;
import com.felipebs.seguranca.payload.request.CadastroRequisicao;
import com.felipebs.seguranca.payload.request.LoginRequisicao;
import com.felipebs.seguranca.payload.response.JwtResponse;
import com.felipebs.seguranca.payload.response.MensagemResponse;
import com.felipebs.seguranca.repository.ICargoAcessoRepositorio;
import com.felipebs.seguranca.repository.IUsuarioRepositorio;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  IUsuarioRepositorio userRepository;

  @Autowired
  ICargoAcessoRepositorio roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequisicao loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<MensagemResponse> registerUser(@Valid @RequestBody CadastroRequisicao signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MensagemResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MensagemResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    Usuario user = new Usuario(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<CargoAcesso> roles = new HashSet<>();

    if (strRoles == null) {
      CargoAcesso userRole = roleRepository.findByName(CargoAcessoEnum.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          CargoAcesso adminRole = roleRepository.findByName(CargoAcessoEnum.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          CargoAcesso modRole = roleRepository.findByName(CargoAcessoEnum.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          CargoAcesso userRole = roleRepository.findByName(CargoAcessoEnum.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MensagemResponse("User registered successfully!"));
  }
}
