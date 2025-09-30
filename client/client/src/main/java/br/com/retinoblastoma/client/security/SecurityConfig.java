package br.com.retinoblastoma.client.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import br.com.retinoblastoma.client.security.provider.UserAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserAuthenticationProvider authenticationProvider;

	public SecurityConfig(UserAuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	/**
	 * Substitui o configure(AuthenticationManagerBuilder auth)
	 */
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(List.of(authenticationProvider));
	}

	/**
	 * Substitui o configure(HttpSecurity http)
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authenticationManager(authenticationManager())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/resources/**")
						.permitAll()
						.requestMatchers("/")
						.permitAll()
						.requestMatchers("/account/register")
						.permitAll()
						.requestMatchers(HttpMethod.POST, "/account/register")
						.permitAll()
						.requestMatchers("/account/register-doctor")
						.permitAll()
						.requestMatchers("/forum/topics")
						.permitAll()
						.requestMatchers("/user/detail")
						.permitAll()
						.requestMatchers("/user/create")
						.permitAll()
						.requestMatchers("/account/password-recovery")
						.hasRole("USUARIO")
						.anyRequest()
						.authenticated())
				.formLogin(form -> form.loginPage("/account/login-page")
						.loginProcessingUrl("/login")
						.permitAll()
						.defaultSuccessUrl("/"))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())
				.exceptionHandling(ex -> ex.accessDeniedPage("/not-found"));

		return http.build();
	}
}
