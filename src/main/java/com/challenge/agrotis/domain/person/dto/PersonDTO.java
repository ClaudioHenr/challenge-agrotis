package com.challenge.agrotis.domain.person.dto;

import java.time.Instant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonDTO(
    
    @NotBlank(message = "Nome é obrigatório")
    String nome,
    
    @NotNull(message = "Data inicial não pode ser nula")
    Instant dataInicial,
    
    @NotNull(message = "Data final não pode ser nula")
    Instant dataFinal,

    @Valid
    @NotNull(message = "Propriedade não pode ser nula")
    PropertyDTO infosPropriedade,

    @Valid
    @NotNull(message = "Laboratório não pode ser nulo")
    LaboratoryDTO laboratorio,

    String observacoes
) { }
