package br.com.retinoblastoma.api.mapper;

import java.time.LocalDateTime;

import br.com.retinoblastoma.model.dto.ResponseDto;
import br.com.retinoblastoma.model.entities.Response;
import br.com.retinoblastoma.model.entities.Topic;
import br.com.retinoblastoma.model.entities.User;

public class ResponseMapper {

	public static Response toResponse(ResponseDto responseDto, User user, Topic topic) {
		return Response.builder()
				.content(responseDto.getContent())
				.author(user)
				.topic(topic)
				.creationDate(LocalDateTime.now())
				.build();
	}

	public static ResponseDto toResponseDto(Response responseSaved) {
		return ResponseDto.builder()
				.id(responseSaved.getId())
				.content(responseSaved.getContent())
				.userId(responseSaved.getAuthor().getId())
				.topicId(responseSaved.getTopic().getId())
				.creationDate(responseSaved.getCreationDate())
				.build();
	}
}
