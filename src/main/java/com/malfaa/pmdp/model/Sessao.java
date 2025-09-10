package com.malfaa.pmdp.model;

import com.malfaa.pmdp.model.enums.Agendamento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String titulo;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    /**
     * Define o status que está o agendamento da sessão
     * [AGENDADA, CONCLUIDA, CANCELADA]
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Agendamento status;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentorado_id")
    private Mentorado mentorado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendario_id")
    private Calendario calendario;

}
