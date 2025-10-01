package br.com.retinoblastoma.api.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.retinoblastoma.model.dto.ResponseDto;
import br.com.retinoblastoma.model.dto.TopicRequestDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;
import br.com.retinoblastoma.model.entities.Topic;
import br.com.retinoblastoma.model.entities.User;

public class TopicMapper {

	public static Topic toTopic(TopicRequestDto topicRequestDto, User user) {
		return Topic.builder()
				.title(topicRequestDto.getTitle())
				.content(topicRequestDto.getContent())
				.author(user)
				.creationDate(LocalDateTime.now())
				.image(topicRequestDto.getImage())
				.build();
	}

	public static TopicResponseDto toResponseDto(Topic topic) {
		List<ResponseDto> responses = topic.getResponses() != null ? topic.getResponses()
				.stream()
				.map(response -> new ResponseDto(
						response.getId(),
						response.getContent(),
						response.getAuthor().getId(),
						response.getAuthor().getName(),
						response.getTopic().getId(),
						response.getCreationDate()))
				.toList() : List.of();

		return TopicResponseDto.builder()
				.id(topic.getId())
				.title(topic.getTitle())
				.content(topic.getContent())
				.author(topic.getAuthor().getName())
				.userId(topic.getAuthor().getId())
				.creationDate(topic.getCreationDate())
				.image(topic.getImage())
				.responses(responses)
				.build();
	}

	public static List<TopicResponseDto> toResponseDtoList(List<Topic> topics) {

		List<TopicResponseDto> topicResponseDtos = new ArrayList<>();
		for (Topic topic : topics) {
			List<ResponseDto> responses = topic.getResponses() != null ? topic.getResponses()
					.stream()
					.map(response -> new ResponseDto(
							response.getId(),
							response.getContent(),
							response.getAuthor().getId(),
							response.getAuthor().getName(),
							response.getTopic().getId(),
							response.getCreationDate()))
					.toList() : List.of();

			topicResponseDtos.add(TopicResponseDto.builder()
					.id(topic.getId())
					.title(topic.getTitle())
					.content(topic.getContent())
					.author(topic.getAuthor().getName())
					.userId(topic.getAuthor().getId())
					.image(topic.getImage())
					.creationDate(topic.getCreationDate())
					.responses(responses)
					.build());
		}

		return topicResponseDtos;
	}

}
