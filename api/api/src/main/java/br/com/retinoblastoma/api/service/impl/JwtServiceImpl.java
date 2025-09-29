package br.com.retinoblastoma.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import br.com.retinoblastoma.api.security.ApiSecurityConstants;
import br.com.retinoblastoma.api.service.JwtService;
import br.com.retinoblastoma.model.entities.User;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {

	@Override
	public String getJWTToken(User user) {

		try {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

			String token = Jwts.builder()
					.setId("RetinoBlastoma")
					.setSubject(user.getUsername())
					.claim("authorities",
							grantedAuthorities.stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 600000))
					.signWith(ApiSecurityConstants.KEY)
					.compact();

			return token;
		} catch (Exception e) {

			System.out.println(e.getMessage());

			return ApiSecurityConstants.INVALID_TOKEN;

		}
	}

}
