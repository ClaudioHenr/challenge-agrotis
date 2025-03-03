package com.challenge.agrotis.domain.laboratory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.agrotis.domain.laboratory.model.Laboratorio;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratorio, Long> {
    
    @Query("""
            SELECT l 
            FROM Laboratorio l 
            LEFT JOIN l.persons p 
            GROUP BY l
            HAVING COUNT(p) >= :minPersons
            ORDER BY l.nome ASC
           """)
    List<Laboratorio> findByMinPersons(@Param("minPersons") Integer minPersons);

}
