package br.com.retinoblastoma.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.retinoblastoma.model.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
