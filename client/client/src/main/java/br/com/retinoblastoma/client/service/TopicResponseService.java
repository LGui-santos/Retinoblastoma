package br.com.retinoblastoma.client.service;

import java.util.List;

import br.com.retinoblastoma.model.dto.TopicResponseDto;

public interface TopicResponseService {

	TopicResponseDto readById(Long id);

	boolean deleteById(Long id);

	List<TopicResponseDto> readAllTopicResponseDto();

}
