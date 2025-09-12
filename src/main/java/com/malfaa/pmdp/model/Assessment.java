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
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer grade;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id", nullable = false)
    private Mentee mentee;

    @OneToOne
    @JoinColumn(name = "session_id", nullable = false, unique = true)
    private Session session;
}