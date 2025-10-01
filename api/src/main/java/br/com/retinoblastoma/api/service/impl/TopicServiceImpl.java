package br.com.retinoblastoma.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.retinoblastoma.api.mapper.TopicMapper;
import br.com.retinoblastoma.api.repository.TopicRepository;
import br.com.retinoblastoma.api.repository.UserRepository;
import br.com.retinoblastoma.model.dto.TopicRequestDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;
import br.com.retinoblastoma.model.entities.Topic;
import br.com.retinoblastoma.model.entities.User;

@Service
@Transactional
public class TopicServiceImpl {

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

	public TopicResponseDto getById(Long id) {
		Topic topic = topicRepository.getReferenceById(id);
		return TopicMapper.toResponseDto(topic);
	}

	public Boolean deleteById(Long id) {

		Optional<Topic> optionalTopic = topicRepository.findById(id);
		if (optionalTopic.get() != null) {
			topicRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
