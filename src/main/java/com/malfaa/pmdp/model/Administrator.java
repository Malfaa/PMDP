package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Usuário responsável pela gestão da plataforma
 *
 * @author Malfaa
 * @version 1.0
 */
@Entity
@Getter
@Setter
@DiscriminatorValue("ADMIN")
public class Administrator extends User {
    @Column(nullable = false)
    private Integer admLevel;
}