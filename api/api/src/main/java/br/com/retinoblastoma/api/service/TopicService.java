package br.com.retinoblastoma.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.retinoblastoma.api.mapper.TopicMapper;
import br.com.retinoblastoma.api.repository.TopicRepository;
import br.com.retinoblastoma.api.repository.UserRepository;
import br.com.retinoblastoma.model.dto.TopicRequestDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;
import br.com.retinoblastoma.model.entities.Topic;
import br.com.retinoblastoma.model.entities.User;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private UserRepository userRepository;

	public TopicResponseDto createTopic(TopicRequestDto dto) {
		User author = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		Topic topic = TopicMapper.toTopic(dto, author);

		Topic topicSaved = topicRepository.save(topic);

		return TopicMapper.toResponseDto(topicSaved);
	}

	public List<TopicResponseDto> getAll() {
		List<Topic> topics = topicRepository.findAll();
		return TopicMapper.toResponseDtoList(topics);
	}

}
