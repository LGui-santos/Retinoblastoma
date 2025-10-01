package br.com.retinoblastoma.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponseDto {

	private Long id;
	private String title;
	private String content;
	private LocalDateTime creationDate;
	private String author;
	private Long userId;
	private byte[] image;
	private List<ResponseDto> responses;
}
