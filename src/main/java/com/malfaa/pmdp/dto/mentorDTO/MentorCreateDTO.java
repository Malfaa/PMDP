package com.malfaa.pmdp.dto.mentorDTO;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Set;

public record MentorCreateDTO(
        @NotBlank(message = "O nome é obrigatório.")
        String name,
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Formato de e-mail inválido.")
        String email,
        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,
        @NotBlank(message = "O CPF é obrigatório.")
        @CPF(message = "CPF inválido.")
        String cpf,
        @NotNull(message = "A data de nascimento é obrigatória.")
        @Past(message = "A data de nascimento deve ser uma data no passado.")
        LocalDate birthday,

        @NotBlank(message = "A descrição do perfil é obrigatória.")
        String profileDescription,

        @NotBlank(message = "A formação acadêmica é obrigatória.")
        String academicFormation,

        @NotBlank(message = "A experiência profissional é obrigatória.")
        String professionalExperience,

        @NotEmpty(message = "O mentor deve ter pelo menos uma categoria de especialidade.")
        Set<Long> categoryIds
        ) {}
