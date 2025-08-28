package com.malfaa.pmdp.model;

import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.*;

/**
 * Representa a entidade Categoriado sistema
 * <p>
 * Esta classe tem como objetivo categorizar os materiais providos
 * pelos mentores, afim de organizar a lista de produtos disponibilizados
 * paras os mentorados
 * 
 * @author Malfaa
 * @version 1.0
 */
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Setter
    @Getter
    private String nome;

    @Setter
    @Getter
    private String descricao;
}