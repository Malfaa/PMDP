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
public class Mentor extends User {

    @Column(columnDefinition = "TEXT")
    private String academicFormation;

    @Column(columnDefinition = "TEXT")
    private String professionalExperience;

    @Column(columnDefinition = "TEXT")
    private String perfilDescription;

    @OneToOne(mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Calendar calendar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mentor_category", joinColumns = @JoinColumn(name = "mentor_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Session> sessions = new HashSet<>();

    @OneToMany(mappedBy = "mentor")
    private Set<Feedback> feedbacks = new HashSet<>();

    @OneToMany(mappedBy = "mentor")
    private Set<SupportingMaterial> supportingMaterial = new HashSet<>();


    public Mentor() {
        categories = new HashSet<>();
    }
}
