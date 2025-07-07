package br.com.retinoblastoma.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String conteudo;

	@ManyToOne
	private User autor;

	private LocalDateTime dataCriacao = LocalDateTime.now();

	@OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
	private List<Response> respostas = new ArrayList<>();

}
