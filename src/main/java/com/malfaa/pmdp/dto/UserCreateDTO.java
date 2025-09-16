package com.malfaa.pmdp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.malfaa.pmdp.model.enums.*;

public record UserCreateDTO( 
    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100)
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

    @NotNull(message = "O tipo de usuário é obrigatório.")
    Perfil type
){}