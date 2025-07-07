package br.com.retinoblastoma.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.retinoblastoma.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
