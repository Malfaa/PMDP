package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("MENTOR")
public class Mentor extends Usuario {

    @Column(columnDefinition = "TEXT")
    private String formacaoAcademica;

    @Column(columnDefinition = "TEXT")
    private String experienciaProfissional;

    @Column(columnDefinition = "TEXT")
    private String descricaoPerfil;

    @OneToOne(mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Calendario calendario;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mentor_categoria", joinColumns = @JoinColumn(name = "mentor_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sessao> sessoes = new HashSet<>();

    @OneToMany(mappedBy = "mentor")
    private Set<Feedback> feedbacks = new HashSet<>();

    @OneToMany(mappedBy = "mentor")
    private Set<MaterialApoio> materiaisDeApoio = new HashSet<>();


    public Mentor() {
        categorias = new HashSet<>();
    }
}
