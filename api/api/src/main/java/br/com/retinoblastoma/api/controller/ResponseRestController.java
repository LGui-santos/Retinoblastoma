package br.com.retinoblastoma.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.model.dto.ResponseDto;

@RestController
@RequestMapping("/response")
@CrossOrigin(origins = "*")
public class ResponseRestController {

	@PostMapping
	public ResponseEntity<ResponseDto> createResponse(@RequestBody ResponseDto responseDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

}
