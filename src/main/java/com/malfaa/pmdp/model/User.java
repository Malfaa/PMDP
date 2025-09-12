package com.malfaa.pmdp.model;

import com.malfaa.pmdp.model.enums.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.br.CPF;

import java.time.*;

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
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
@SQLDelete(sql = "UPDATE usuario SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deletedAt IS NULL")
public class User {
    /**
     * O identificador único do usuário.
     * <p>
     * Atributo chave primária (PK) no banco de dados.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome completo do usuário.
     */
    private String name;

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
    private String password;

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
    @Column(name = "birthday")
    private LocalDate birthday;

    /**
     * O tipo de perfil do usuário, definido por um enum.
     * <p>
     * Indica o nível de acesso e permissões do usuário no sistema.
     * </p>
     */
    private Perfil type;

    /**
     * Registra quano que este usuário foi criado no banco.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);

    /**
     * Registra quando que esse usuário foi removido. (SOFT DELETE)
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * Construtor padrão sem argumentos.
     * <p>
     * Exigido pelo JPA para criar instâncias da entidade.
     * </p>
     */
    public User(){}

}