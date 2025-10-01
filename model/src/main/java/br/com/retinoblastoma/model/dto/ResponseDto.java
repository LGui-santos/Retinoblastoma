package br.com.retinoblastoma.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

	private Long id;
	private String content;
	private Long userId;
	private String author;
	private Long topicId;
	private LocalDateTime creationDate;
	private byte[] image;
}
