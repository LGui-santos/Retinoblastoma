package br.com.retinoblastoma.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
	@SequenceGenerator(name = "topic_seq", sequenceName = "topic_id_seq", allocationSize = 1)
	private Long id;
	@Column(nullable = false, length = 150)
	private String name;
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	@Column(nullable = false, unique = true, length = 50)
	private String username;
	@Column(nullable = false, length = 100)
	@JsonIgnore
	private String password;
	@Column(nullable = false)
	private String role;

	private String token;

}
