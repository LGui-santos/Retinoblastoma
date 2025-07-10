package br.com.retinoblastoma.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.retinoblastoma.model.entities.Doctor;

@RestController
@RequestMapping("/api/v1/doctor")
@CrossOrigin(origins = "*")
public class DoctorRestController {

	public ResponseEntity<Long> createDoctor(Doctor doctor) {
		return null;
	}

}
