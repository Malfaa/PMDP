package com.malfaa.pmdp.model;

import java.time.LocalTime;

import com.malfaa.pmdp.util.Agendamento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String titulo;

    @Column(name = "data_hora")
    private LocalTime dataHora;

    @Enumerated(EnumType.STRING)
    private Agendamento status;

    private double preco;
    private Mentorado mentorado;
    private Mentor mentor;
    private Categoria categoria;

}
