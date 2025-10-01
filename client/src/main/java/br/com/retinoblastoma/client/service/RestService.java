package br.com.retinoblastoma.client.service;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.retinoblastoma.client.security.CustomUserDetails;
import br.com.retinoblastoma.model.dto.UserDto;

public class RestService {

	public static HttpHeaders getAuthenticationHeaders(String username, String password) {

		String auth = "Username=" + username + ";Password=" + password;

		byte[] encodedBytes;

		try {

			encodedBytes = Base64.getEncoder().encode(auth.getBytes("utf-8"));

			System.out.println("encoded bytes: " + new String(encodedBytes));

			String header = "Basic " + new String(encodedBytes);

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", header);

			return headers;
		} catch (Exception e) {

			return null;
		}
	}

	public static HttpHeaders getRequestHeaders() {

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			UserDto usuarioLogado = userDetails.getUsuario();

			System.out.println("getRequestHeaders " + usuarioLogado.getToken());

			String authHeader = "Bearer " + usuarioLogado.getToken();

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", authHeader);

			return headers;
		} catch (Exception e) {
			e.printStackTrace();
			return new HttpHeaders();
		}
	}

}
