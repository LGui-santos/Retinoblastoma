package br.com.retinoblastoma.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.api.service.impl.UserServiceImpl;
import br.com.retinoblastoma.model.dto.UserDto;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	UserServiceImpl userServiceImpl;

	public UserRestController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody UserDto userDto) {

		Long userId = userServiceImpl.create(userDto);
		return ResponseEntity.created(null).body(userId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> readById(@PathVariable Long id) {
		UserDto userDto = userServiceImpl.readById(id);
		return ResponseEntity.ok(userDto);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {

		boolean response = userServiceImpl.deleteById(id);

		return ResponseEntity.ok(response);
	}
}
