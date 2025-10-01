package br.com.retinoblastoma.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.retinoblastoma.model.entities.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {

}
