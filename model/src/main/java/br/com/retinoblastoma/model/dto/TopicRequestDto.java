package br.com.retinoblastoma.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.retinoblastoma.model.entities.User;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicRequestDto {

	private Long id;
	private User author;
	private String title;
	private String content;
	private Long userId;
	private byte[] image;
	private LocalDateTime creationDate;
}
