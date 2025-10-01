package br.com.retinoblastoma.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.api.service.BaseService;
import br.com.retinoblastoma.model.dto.DoctorDto;

@RestController
@RequestMapping("/api/v1/doctor")
@CrossOrigin(origins = "*")
public class DoctorRestController {

	@Autowired
	BaseService<DoctorDto> doctorService;

	@PostMapping
	public ResponseEntity<Long> createDoctor(@RequestBody DoctorDto doctorDto) {

		Long userId = doctorService.create(doctorDto);

		return ResponseEntity.created(null).body(userId);
	}

}
