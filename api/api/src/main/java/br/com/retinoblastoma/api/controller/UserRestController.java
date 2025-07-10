package br.com.retinoblastoma.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.api.service.UserService;
import br.com.retinoblastoma.model.dto.UserDto;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {

		return ResponseEntity.created(null).body(userService.saveUser(userDto));
	}
}
