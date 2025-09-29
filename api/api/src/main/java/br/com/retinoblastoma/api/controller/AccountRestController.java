package br.com.retinoblastoma.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.api.service.UserService;
import br.com.retinoblastoma.model.dto.UserDto;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*")
public class AccountRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestHeader("Authorization") String encodedData) {

		System.out.println("Chegou a request com o base64: " + encodedData);

		UserDto userDto = userService.validateLogin(encodedData);

		if (userDto == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(userDto);
	}

}
