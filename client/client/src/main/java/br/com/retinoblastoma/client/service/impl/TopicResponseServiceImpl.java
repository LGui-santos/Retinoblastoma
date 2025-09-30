package br.com.retinoblastoma.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.retinoblastoma.client.security.CustomUserDetails;
import br.com.retinoblastoma.client.service.RestService;
import br.com.retinoblastoma.client.service.TopicResponseService;
import br.com.retinoblastoma.model.dto.ResponseDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;
import br.com.retinoblastoma.model.dto.UserDto;

@Service
public class TopicResponseServiceImpl implements TopicResponseService {

//	@Override
//	public List<TopicResponseDto> readAll() {
//		return null;
//	}

//	@Override
//	public Long create(TopicResponseDto entity) {
//		return null;
//	}

	@Override
	public TopicResponseDto readById(Long id) {
		TopicResponseDto response = null;

		String endpoint = "http://localhost:8082/api/v1/topic/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		try {

			HttpHeaders headers = RestService.getRequestHeaders();
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<TopicResponseDto> requestResponse = restTemplate
					.exchange(endpoint, HttpMethod.GET, requestEntity, TopicResponseDto.class);

			response = requestResponse.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

//	@Override
//	public boolean update(TopicResponseDto entity) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean deleteById(Long id) {

		String endpoint = "http://localhost:8082/api/response/delete/" + id;
		RestTemplate restTemplate = new RestTemplate();
		try {
			// headers com token do usuário logado
			HttpHeaders headers = RestService.getRequestHeaders();
			HttpEntity<Void> entity = new HttpEntity<>(headers);

			// envia DELETE para a API
			restTemplate.exchange(endpoint, HttpMethod.DELETE, entity, Void.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<TopicResponseDto> readAllTopicResponseDto() {
		List<TopicResponseDto> response = null;

		String endpoint = "http://localhost:8082/api/v1/topic/read-all";

		RestTemplate restTemplate = new RestTemplate();
		try {

			HttpHeaders headers = RestService.getRequestHeaders();

			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<TopicResponseDto[]> requestResponse = restTemplate
					.exchange(endpoint, HttpMethod.GET, requestEntity, TopicResponseDto[].class);
			TopicResponseDto[] users = requestResponse.getBody();
			response = Arrays.asList(users);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	public TopicResponseDto sendResponse(ResponseDto responseDto) {
		TopicResponseDto result = null;

		// pega user logado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		UserDto usuarioLogado = userDetails.getUsuario();

		// seta userId e topicId no DTO antes de enviar
		responseDto.setUserId(usuarioLogado.getId());
		String endpoint = "http://localhost:8082/api/response";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpHeaders headers = RestService.getRequestHeaders();

			HttpEntity<ResponseDto> httpEntity = new HttpEntity<ResponseDto>(responseDto, headers);
			ResponseEntity<TopicResponseDto> requestResponse = restTemplate
					.exchange(endpoint, HttpMethod.POST, httpEntity, TopicResponseDto.class);
			result = requestResponse.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
