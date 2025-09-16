package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Review;
import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMentee(Mentee mentee);
    Optional<Review> findBySession(Session session);
    List<Review> findByGrade(Integer grade);
}
