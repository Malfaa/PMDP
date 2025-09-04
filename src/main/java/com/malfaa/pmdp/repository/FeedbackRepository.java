package com.malfaa.pmdp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malfaa.pmdp.model.Feedback;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.model.Mentorado;
import com.malfaa.pmdp.model.Sessao;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByMentor(Mentor mentor);
    List<Feedback> findByMentorado(Mentorado mentorado);
    Optional<Feedback> findBySessao(Sessao sessao);
}
