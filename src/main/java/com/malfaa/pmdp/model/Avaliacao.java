package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Avaliação pública feita pelo mentorado após a sessão
 *
 * @author Malfaa
 * @version 1.0
 */
@Entity
@Getter
@Setter
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentorado_id", nullable = false)
    private Mentorado mentorado;

    @OneToOne
    @JoinColumn(name = "sessao_id", nullable = false, unique = true)
    private Sessao sessao;
}