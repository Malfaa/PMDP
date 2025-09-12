package com.malfaa.pmdp.repository;

import java.util.List;

import com.malfaa.pmdp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malfaa.pmdp.model.Calendar;
import com.malfaa.pmdp.model.Category;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.model.enums.Scheduling;

@Repository
public interface SessaoRepository extends JpaRepository<Session, Long> {
    List<Session> findByMentor(Mentor mentor);
    List<Session> findByCategoria(Category category);
    List<Session> findByStatus(Scheduling status);
    List<Session> findByDate(Calendar calendar);
}