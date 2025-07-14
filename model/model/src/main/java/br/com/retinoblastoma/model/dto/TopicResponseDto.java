package br.com.retinoblastoma.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicResponseDto {

	private Long id;
	private String title;
	private String content;
	private LocalDateTime creationDate;
	private String authorName;
	private List<ResponseDto> responses;
}
