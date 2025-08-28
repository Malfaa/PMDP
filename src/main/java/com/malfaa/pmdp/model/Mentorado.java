package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("MENTORADO")
public class Mentorado extends Usuario{
    private String progresso;
    private List<String> interesses;
    
}