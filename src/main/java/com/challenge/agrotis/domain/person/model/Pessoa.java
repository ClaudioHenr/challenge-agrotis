package com.challenge.agrotis.domain.person.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String nome;
    
    @Column
    private Instant dataInicial;
    
    @Column
    private Instant dataFinal;

    @OneToOne
    @JoinColumn(name = "id_propriedade")
    private Propriedade infosPropriedade;

    @ManyToOne
    private Laboratorio laboratorio;

    @Column
    private String observacoes;

    public Pessoa(String nome, Instant dateInicial, Instant dateFinal, Propriedade infosPropriedade, Laboratorio laboratorio, String observacoes) {
        this.nome = nome;
        this.dataInicial = dateInicial;
        this.dataFinal = dateFinal;
        this.infosPropriedade = infosPropriedade;
        this.laboratorio = laboratorio;
        this.observacoes = observacoes;
    }

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (dataInicial.isAfter(dataFinal)) {
            throw new IllegalArgumentException("A data inicial não pode ser maior que a data final");
        }
    }
}
