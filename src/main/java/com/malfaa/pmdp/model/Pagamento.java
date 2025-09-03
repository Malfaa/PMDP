package com.malfaa.pmdp.model;

import com.malfaa.pmdp.model.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamento status;

    @Column(name = "metodo_pagamento", columnDefinition = "TEXT", nullable = false)
    private String metodoPagamento;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", nullable = false)
    private Sessao sessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentorado_id", nullable = false)
    private Mentorado mentorado;
}