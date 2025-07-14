package br.com.retinoblastoma.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.api.service.TopicService;
import br.com.retinoblastoma.model.dto.TopicRequestDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;

@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = "*")
public class TopicRestController {

	@Autowired
	TopicService topicService;

	@PostMapping
	public ResponseEntity<TopicResponseDto> createTopic(@RequestBody TopicRequestDto topic) {

		return ResponseEntity.status(HttpStatus.CREATED).body(topicService.createTopic(topic));
	}
}
