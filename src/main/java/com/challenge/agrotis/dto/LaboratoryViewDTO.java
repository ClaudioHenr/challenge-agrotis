package com.challenge.agrotis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LaboratoryViewDTO(
    
    @NotNull(message = "Id não pode ser nulo")
    Long id,

    @NotBlank(message = "Nome do Laboratório é obrigatório")
    String nome,

    Integer numberPeople

) { }
