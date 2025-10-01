package br.com.retinoblastoma.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime creationDate;

	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	@OrderBy("creationDate ASC") // ou DESC para mais recentes primeiro
	private List<Response> responses = new ArrayList<>();

    @Lob
    @Basic(fetch = FetchType.EAGER) // <--- Garante que os bytes sejam carregados ANTES da sessão fechar
    @Column(name = "image", columnDefinition = "BYTEA")
    private byte[] image;

}
