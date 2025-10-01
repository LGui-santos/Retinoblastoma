package br.com.retinoblastoma.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.retinoblastoma.api.security.filter.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class RestSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// Desabilita CSRF
				.csrf(csrf -> csrf.disable())
				// Desliga o formLogin padrão e o httpBasic
				.formLogin(form -> form.disable())
				.httpBasic(basic -> basic.disable())

				// Adiciona o filtro JWT depois do UsernamePasswordAuthenticationFilter
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)

				// Configuração de autorização
				.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/api/account/login")
						.permitAll()
						.requestMatchers("/api/v1/user/create")
						.permitAll()
						.anyRequest()
						.authenticated());

		return http.build();
	}
}
