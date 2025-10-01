package br.com.retinoblastoma.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.com.retinoblastoma.client.service.RestService;
import br.com.retinoblastoma.client.service.UserService;
import br.com.retinoblastoma.model.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

	@Override
	public List<UserDto> readAll() {
		List<UserDto> response = null;

		String endpoint = apiBaseUrl + "/api/v1/user/read-all";

		RestTemplate restTemplate = new RestTemplate();
		try {

			HttpHeaders headers = RestService.getRequestHeaders();

			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<UserDto[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, requestEntity,
					UserDto[].class);
			UserDto[] users = requestResponse.getBody();
			response = Arrays.asList(users);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	@Override
	public UserDto readById(Long id) {
		UserDto response = null;

		String endpoint = apiBaseUrl + "/api/v1/user/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		try {
			
			HttpHeaders headers = RestService.getRequestHeaders();
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<UserDto> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, requestEntity,
					UserDto.class);

			response = requestResponse.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean updateAvatar(Long userId, MultipartFile file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDto validateUsernameAndPassword(String username, String password) {
		UserDto user = null;

		String endpoint = apiBaseUrl + "/api/account/login";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpHeaders httpHeaders = RestService.getAuthenticationHeaders(username, password);
			System.out.println(RestService.getAuthenticationHeaders(username, password));

			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

			ResponseEntity<UserDto> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					UserDto.class);

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				return null;
			}

			user = responseEntity.getBody();

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return user;
	}

	public Long create(UserDto entity) {

		Long id = Long.valueOf(0);
		String endpoint = apiBaseUrl + "/api/v1/user/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<UserDto> httpEntity = new HttpEntity<UserDto>(entity);

			ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					String.class);

			String response = responseEntity.getBody();
			id = Long.parseLong(response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return id;
	}

}
