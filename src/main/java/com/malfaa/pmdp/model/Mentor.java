package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("MENTOR")
public class Mentor extends Usuario {
    private List<Categoria> categorias;
    private String descricaoPerfil;
    private Calendario calendario;

}
