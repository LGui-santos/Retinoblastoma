package br.com.retinoblastoma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Doctor extends User {

	@Column
	private String crm;
	@Column
	private String especialidade;

}
