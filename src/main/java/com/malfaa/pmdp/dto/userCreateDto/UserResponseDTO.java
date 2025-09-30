package com.malfaa.pmdp.dto.userCreateDto;

import com.malfaa.pmdp.model.enums.Perfil;

public record UserResponseDTO(
    Long id,
    String name,
    String email,
    Perfil type
){}
