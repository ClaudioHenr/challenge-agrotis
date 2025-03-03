package com.challenge.agrotis.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record PropertyDTO(
    
    @NotNull(message = "Id não pode ser nulo")
    Long id,

    @NotBlank(message = "Nome da Propriedade é obrigatória")
    String nome
    
) { }
