package br.com.retinoblastoma.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.api.service.impl.TopicServiceImpl;
import br.com.retinoblastoma.model.dto.TopicRequestDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;

@RestController
@RequestMapping("/api/v1/topic")
@CrossOrigin(origins = "*")
public class TopicRestController {

	@Autowired
	TopicServiceImpl topicServiceImpl;

	@PostMapping("/create-topic")
	public ResponseEntity<TopicResponseDto> createTopic(@RequestBody TopicRequestDto topic) {

		TopicResponseDto response = topicServiceImpl.createTopic(topic);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/read-all")
	public ResponseEntity<List<TopicResponseDto>> listTopics() {
		return ResponseEntity.status(HttpStatus.OK).body(topicServiceImpl.getAll());
	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<TopicResponseDto> readyById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(topicServiceImpl.getById(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {

		boolean response = topicServiceImpl.deleteById(id);

		return ResponseEntity.ok(response);
	}
}
