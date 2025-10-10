package com.malfaa.pmdp.dto;

import jakarta.validation.constraints.NotNull;

public record ReviewDTO(
        Long id,
        @NotNull(message = "Grade não pode ser nulo.")
        Integer grade,
        String comment
) {}
