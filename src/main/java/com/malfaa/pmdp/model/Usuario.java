package com.malfaa.pmdp.model;

import com.malfaa.pmdp.util.Perfil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

/**
 * Representa a entidade de um usuário no sistema.
 * <p>
 * Esta classe é utilizada para mapear a tabela 'usuario' no banco de dados.
 * Contém dados essenciais de um usuário, como informações de identificação,
 * contato e autenticação.
 * </p>
 *
 * @author Malfaa
 * @version 1.0
 */

@Getter
@Entity
public class Usuario {
    /**
     * O identificador único do usuário.
     * <p>
     * Atributo chave primária (PK) no banco de dados.
     * </p>
     */
    @Id
    private int id;

    /**
     * O nome completo do usuário.
     */
    @Setter
    private String nome;

    /**
     * O endereço de e-mail do usuário.
     * <p>
     * Deve ser único para cada usuário e usado para autenticação.
     * </p>
     */
    @Column(unique = true)
    @Email
    private String email;

    /**
     * A senha de acesso do usuário.
     * <p>
     * Armazenada de forma segura (normalmente como um hash).
     * </p>
     */
    @Setter
    private String senha;

    /**
     * O CPF (Cadastro de Pessoas Físicas) do usuário.
     * <p>
     * Valor único para fins de identificação.
     * </p>
     */
    @Column(unique = true)
    @CPF
    private String cpf;

    /**
     * A data de nascimento do usuário.
     * <p>
     * Armazena apenas a data, sem a informação de tempo.
     * </p>
     */
    @Setter
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    /**
     * O tipo de perfil do usuário, definido por um enum.
     * <p>
     * Indica o nível de acesso e permissões do usuário no sistema.
     * </p>
     */
    @Setter
    private Perfil tipo;

    /**
     * Construtor padrão sem argumentos.
     * <p>
     * Exigido pelo JPA para criar instâncias da entidade.
     * </p>
     */
    public Usuario(){}

}

