package br.com.retinoblastoma.model.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
	@SequenceGenerator(name = "topic_seq", sequenceName = "topic_id_seq", allocationSize = 1)
	private Long id;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne
	private User author;

	@ManyToOne
	private Topic topic;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime creationDate;

	private byte[] image;

}
