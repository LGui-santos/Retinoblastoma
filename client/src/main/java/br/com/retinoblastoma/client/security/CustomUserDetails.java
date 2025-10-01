package br.com.retinoblastoma.client.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.retinoblastoma.model.dto.UserDto;

public class CustomUserDetails extends User {

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			UserDto user) {

		super(username, password, true, true, true, true, authorities);

		this.user = user;

	}

	private UserDto user;

	public UserDto getUsuario() {
		return user;
	}

}
