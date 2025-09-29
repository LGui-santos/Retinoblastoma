package br.com.retinoblastoma.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDto {

	private Long id;
	@NotBlank
	private String name;
	@NotBlank()
	@Size(min = 10, max = 50)
	@Email
	private String email;
	@NotBlank
	@Size(min = 4, max = 50)
	private String username;
	@NotBlank
	@Size(min = 6, max = 20)
	private String password;
	@NotBlank
	private String role;
	@NotBlank
	private String crm;
	@NotBlank
	private String specialty;

}
