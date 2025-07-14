package br.com.retinoblastoma.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicRequestDto {

	private String title;
	private String content;
	private Long userId;
}
