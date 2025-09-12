package com.malfaa.pmdp.repository;

import java.util.List;
import java.util.Optional;

import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malfaa.pmdp.model.Feedback;
import com.malfaa.pmdp.model.Mentor;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByMentor(Mentor mentor);
    List<Feedback> findByMentee(Mentee mentee);
    Optional<Feedback> findBySession(Session session);
}
