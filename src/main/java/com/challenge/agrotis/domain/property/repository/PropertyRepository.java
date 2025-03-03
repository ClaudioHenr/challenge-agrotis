package com.challenge.agrotis.domain.property.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.agrotis.domain.property.model.Propriedade;

@Repository
public interface PropertyRepository extends JpaRepository<Propriedade, Long> {

}
