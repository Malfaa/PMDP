package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Assessment;
import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> findByMentee(Mentee mentee);
    Optional<Assessment> findBySession(Session session);
    List<Assessment> findByGrade(Integer grade);
}
