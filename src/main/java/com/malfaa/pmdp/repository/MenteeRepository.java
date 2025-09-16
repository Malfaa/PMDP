package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long>{
    List<Mentee> findByInterests(Set<String> interests);
}
