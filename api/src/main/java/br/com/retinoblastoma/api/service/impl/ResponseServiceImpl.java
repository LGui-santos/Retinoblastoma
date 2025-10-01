package br.com.retinoblastoma.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.retinoblastoma.api.mapper.ResponseMapper;
import br.com.retinoblastoma.api.repository.ResponseRepository;
import br.com.retinoblastoma.api.repository.TopicRepository;
import br.com.retinoblastoma.api.repository.UserRepository;
import br.com.retinoblastoma.model.dto.ResponseDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;
import br.com.retinoblastoma.model.entities.Response;
import br.com.retinoblastoma.model.entities.Topic;
import br.com.retinoblastoma.model.entities.User;

@Service
@Transactional
public class ResponseServiceImpl {

	@Autowired
	ResponseRepository responseRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	TopicServiceImpl topicServiceImpl;

	public TopicResponseDto createResponse(ResponseDto responseDto) {

		User user = userRepository.findById(responseDto.getUserId())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		Topic topic = topicRepository.findById(responseDto.getTopicId())
				.orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

		Response response = ResponseMapper.toResponse(responseDto, user, topic);
		responseRepository.save(response);

		return topicServiceImpl.getById(topic.getId());

//		return ResponseMapper.toResponseDto(responseSaved);
	}

	public Boolean deleteResponse(Long id) {

		Optional<Response> optionalResponse = responseRepository.findById(id);
		Response response = optionalResponse.get();

		if (response != null) {
			responseRepository.deleteById(response.getId());
			return true;
		}
		return false;
	}

}
