package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentorado_id", nullable = false)
    private Mentorado mentorado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", unique = true)
    private Sessao sessao;
}
