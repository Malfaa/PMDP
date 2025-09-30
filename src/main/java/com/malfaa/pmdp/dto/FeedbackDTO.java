package com.malfaa.pmdp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FeedbackDTO(
    Long id,
    @NotBlank(message = "O comentário é obrigatório.")
    @Size(min=10, max=150, message = "O comentário deve ser entre 5 e 150 caracteres.")
    String comment
){}
