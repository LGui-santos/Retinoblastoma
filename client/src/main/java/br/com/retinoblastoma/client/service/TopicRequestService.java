package br.com.retinoblastoma.client.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.retinoblastoma.model.dto.TopicRequestDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;

public interface TopicRequestService extends BaseCrudService<TopicRequestDto> {

	TopicResponseDto createTopic(TopicRequestDto topicRequestDto, MultipartFile file);

	List<TopicResponseDto> readAllTopicResponseDto();

	boolean deleteById(Long id);

}
