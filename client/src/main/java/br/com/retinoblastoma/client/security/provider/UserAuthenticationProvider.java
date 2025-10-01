package br.com.retinoblastoma.client.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.retinoblastoma.client.security.CustomUserDetails;
import br.com.retinoblastoma.client.service.UserService;
import br.com.retinoblastoma.model.dto.UserDto;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    System.out.println("🔥🔥🔥 AUTHENTICATION PROVIDER INICIADO 🔥🔥🔥");
	    System.out.println("🔥 Username: " + authentication.getName());
	    System.out.println("🔥 Password existe: " + (authentication.getCredentials() != null));
	    
	    String username = authentication.getName();
	    String password = authentication.getCredentials().toString();

	    try {
	        System.out.println("🔥 Chamando validateUsernameAndPassword...");
	        UserDto user = userService.validateUsernameAndPassword(username, password);

	        if (user == null) {
	            System.out.println("🔥 USER É NULL - lançando BadCredentialsException");
	            throw new BadCredentialsException("Usuário ou senha inválidos");
	        }

	        System.out.println("🔥 USER ENCONTRADO: " + user.getUsername());
	        
	        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
	        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

	        UserDetails principal = new CustomUserDetails(username, password, grantedAuthorityList, user);

	        System.out.println("🔥 AUTENTICAÇÃO SUCESSO!");
	        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuthorityList);
	        
	    } catch (Exception e) {
	        System.out.println("🔥 ERRO: " + e.getClass().getSimpleName() + " - " + e.getMessage());
	        e.printStackTrace();
	        throw new BadCredentialsException("Erro na autenticação: " + e.getMessage());
	    }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public UserDto getAuthenticatedUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		return userDetails.getUsuario();
	}

}
