package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("MENTORADO")
public class Mentorado extends Usuario{
    @Column(columnDefinition = "TEXT")
    private String progresso;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "mentorado_interesses", joinColumns = @JoinColumn(name = "mentorado_id"))
    @Column(name = "interesses")
    private Set<String> interesses = new HashSet<>();

    @OneToMany(mappedBy = "mentorado")
    private Set<Sessao> sessoes = new HashSet<>();

    @OneToMany(mappedBy = "mentorado")
    private Set<Avaliacao> avaliacoes = new HashSet<>();

}