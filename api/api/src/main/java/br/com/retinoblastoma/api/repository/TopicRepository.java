package br.com.retinoblastoma.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.retinoblastoma.model.entities.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
