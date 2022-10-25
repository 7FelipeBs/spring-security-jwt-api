package com.felipebs.seguranca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled =  true)
public class WebConfigSecurity {

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		// configs 5.7+
		return http
				.httpBasic()
				.and()
				.authorizeHttpRequests()
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
