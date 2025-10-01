package br.com.retinoblastoma.api.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.retinoblastoma.api.security.ApiSecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {

			if (!checkJwtToken(request)) {
				SecurityContextHolder.clearContext();
				return;
			}

			Jws<Claims> claims = validateToken(request);

			if (claims == null || claims.getBody().get("authorities") == null) {
				SecurityContextHolder.clearContext();

				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN,
						"Voce esta proibido de acessar esse recurso!");

				return;
			}

			setupAuthentication(claims.getBody());

		} catch (Exception e) {

			System.out.println(e.getMessage());

			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN,
					"Voce esta proibido de acessar esse recurso!");

		} finally {
			filterChain.doFilter(request, response);
		}

	}

	private boolean checkJwtToken(HttpServletRequest request) {

		String authenticationHeader = request.getHeader(ApiSecurityConstants.HEADER);

		if (authenticationHeader == null || !authenticationHeader.startsWith(ApiSecurityConstants.PREFIX)) {
			return false;
		}

		return true;
	}

	private Jws<Claims> validateToken(HttpServletRequest request) {

		System.out.println(request.getHeader(ApiSecurityConstants.HEADER));
		String jwtToken = request.getHeader(ApiSecurityConstants.HEADER).replace(ApiSecurityConstants.PREFIX, "");

		return Jwts.parserBuilder().setSigningKey(ApiSecurityConstants.KEY).build().parseClaimsJws(jwtToken);

	}

	private void setupAuthentication(Claims claims) {

		List<String> authorities = (List<String>) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				claims.getSubject(),
				null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
