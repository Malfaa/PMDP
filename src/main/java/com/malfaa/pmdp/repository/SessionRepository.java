package com.malfaa.pmdp.repository;

import java.util.List;

import com.malfaa.pmdp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.malfaa.pmdp.model.Calendar;
import com.malfaa.pmdp.model.Category;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.model.enums.Scheduling;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByMentor(Mentor mentor);
    List<Session> findByCategory(Category category);
    List<Session> findByStatus(Scheduling status);
    List<Session> findByDate(Calendar calendar);

    //Testar
    @Query("SELECT id, title, dateTime, calendar FROM Session WHERE mentee.deletedAt IS NULL")
    List<Session> findAllActiveSessions();
}