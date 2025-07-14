package br.com.retinoblastoma.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

	private Long id;
	private String content;
	private String authorName;
	private String dateCreation;
}
