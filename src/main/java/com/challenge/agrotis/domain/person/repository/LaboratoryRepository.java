package com.challenge.agrotis.domain.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.agrotis.domain.person.model.Laboratorio;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratorio, Long> {

}
