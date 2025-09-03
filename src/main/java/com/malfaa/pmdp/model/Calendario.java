package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de apoio para gerenciar os horários e a disponibilidade de um mentor.
 *
 * @author Malfaa
 * @version 1.0
 */

@Entity
@Getter
@Setter
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false, unique = true)
    private Mentor mentorAssociado;

    @OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sessao> sessao = new HashSet<>();
}
