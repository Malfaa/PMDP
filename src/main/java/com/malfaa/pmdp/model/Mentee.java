package com.malfaa.pmdp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("MENTEE")
public class Mentee extends User {
    @Column(columnDefinition = "TEXT")
    private String progress;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "mentee_interests", joinColumns = @JoinColumn(name = "mentee_id"))
    @Column(name = "interests")
    private Set<String> interests = new HashSet<>();

    @OneToMany(mappedBy = "mentee")
    private Set<Session> sessions = new HashSet<>();

    @OneToMany(mappedBy = "mentee")
    private Set<Review> reviews = new HashSet<>();

}