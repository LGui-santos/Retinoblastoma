package br.com.retinoblastoma.model.entities;

import jakarta.persistence.Entity;

@Entity
public class Doctor extends User {

	private String crm;
	private String specialty;

}
