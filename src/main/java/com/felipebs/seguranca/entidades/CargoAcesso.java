package com.felipebs.seguranca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.felipebs.seguranca.constante.seguranca.TipoAcessoEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CARGO_ACESSO")
public class CargoAcesso implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 24102022224L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARGO_ACESSO_ID")
    private Long id;
    
    @Column(name = "NOME_CARGO", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAcessoEnum nomeCargo;
    

	@Override
	public String getAuthority() {
		return this.nomeCargo.toString();
	}
}
