package com.challenge.agrotis.domain.laboratory.model;

import java.util.List;

import com.challenge.agrotis.domain.person.model.Pessoa;
import com.challenge.agrotis.dto.LaboratoryDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Laboratorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String nome;

    @OneToMany(mappedBy = "laboratorio", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Pessoa> persons;

    public Laboratorio(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Laboratorio(LaboratoryDTO laboratoryDTO) {
        this.id = laboratoryDTO.id();
        this.nome = laboratoryDTO.nome();
    }
}
