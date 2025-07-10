package br.com.retinoblastoma.model.entities;

import jakarta.persistence.Entity;

@Entity
public class Doctor extends User {

	Doctor(Long id, String name, String email, String password, String role) {
		super(id, name, email, password, role);
		// TODO Auto-generated constructor stub
	}

	private String crm;
	private String specialty;

}
