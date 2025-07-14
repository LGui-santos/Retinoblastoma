package br.com.retinoblastoma.model.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Doctor extends User {

	private String crm;
	private String specialty;

}
