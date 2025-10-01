package br.com.retinoblastoma.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.api.service.impl.ResponseServiceImpl;
import br.com.retinoblastoma.model.dto.ResponseDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;

@RestController
@RequestMapping("/api/response")
@CrossOrigin(origins = "*")
public class ResponseRestController {

	@Autowired
	ResponseServiceImpl responseServiceImpl;

	@PostMapping
	public ResponseEntity<TopicResponseDto> createResponse(@RequestBody ResponseDto responseDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(responseServiceImpl.createResponse(responseDto));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteResponse(@PathVariable("id") Long id) {

		boolean response = responseServiceImpl.deleteResponse(id);

		return ResponseEntity.ok(response);
	}

}
