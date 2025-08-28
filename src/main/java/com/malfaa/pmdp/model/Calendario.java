package com.malfaa.pmdp.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
public class Calendario {
    @Getter
    @Setter
    private Mentor mentorAssociado;

    @Getter
    @Setter
    private List<Sessao> sessao;

}
