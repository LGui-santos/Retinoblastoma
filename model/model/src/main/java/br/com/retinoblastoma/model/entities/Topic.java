package br.com.retinoblastoma.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;

	@ManyToOne
	private User author;
	private LocalDateTime creationDate = LocalDateTime.now();

	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private List<Response> responses = new ArrayList<>();

}
