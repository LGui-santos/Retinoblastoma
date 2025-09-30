package br.com.retinoblastoma.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.com.retinoblastoma.client.service.RestService;
import br.com.retinoblastoma.client.service.TopicRequestService;
import br.com.retinoblastoma.model.dto.TopicRequestDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;

@Service
public class TopicRequestServiceImpl implements TopicRequestService {

	@Override
	public List<TopicRequestDto> readAll() {
		return null;
	}

//	@Override
//	public Long create(TopicRequestDto entity) {
//		return null;
//	}

	@Override
	public TopicRequestDto readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public boolean update(TopicRequestDto entity) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean deleteById(Long id) {
		boolean response = false;

		String endpoint = "http://localhost:8082/api/v1/topic/delete/" + id;

		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpHeaders headers = RestService.getRequestHeaders();

			HttpEntity<TopicRequestDto> httpEntity = new HttpEntity<TopicRequestDto>(headers);

			ResponseEntity<Boolean> responseEntity = restTemplate
					.exchange(endpoint, HttpMethod.DELETE, httpEntity, Boolean.class);

			response = responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public TopicResponseDto createTopic(TopicRequestDto topicRequestDto, MultipartFile file) {
		String endpoint = "http://localhost:8082/api/v1/topic/create-topic";

		RestTemplate restTemplate = new RestTemplate();

		try {
			// aqui você só preenche o byte[] do DTO
			topicRequestDto.setImage(file.getBytes());

			HttpHeaders headers = RestService.getRequestHeaders();
			HttpEntity<TopicRequestDto> httpEntity = new HttpEntity<>(topicRequestDto, headers);

			ResponseEntity<TopicResponseDto> responseEntity = restTemplate
					.exchange(endpoint, HttpMethod.POST, httpEntity, TopicResponseDto.class);

			if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
				return responseEntity.getBody();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

//	@Override
//	public TopicResponseDto createTopic(TopicRequestDto entity) {
//		TopicResponseDto response = null;
//		String endpoint = "http://localhost:8082/api/v1/topic/create-topic";
//		RestTemplate restTemplate = new RestTemplate();
//
//		try {
//			HttpHeaders headers = RestService.getRequestHeaders();
//			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//			HttpEntity<TopicRequestDto> httpEntity = new HttpEntity<TopicRequestDto>(entity, headers);
//
//			ResponseEntity<TopicResponseDto> responseEntity = restTemplate
//					.exchange(endpoint, HttpMethod.POST, httpEntity, TopicResponseDto.class);
//
//			response = responseEntity.getBody();
////			id = Long.parseLong(response);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		return response;
//	}

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

}
