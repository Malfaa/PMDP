package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Category;
import com.malfaa.pmdp.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByAcademicFormation(String formation);
    List<Mentor> findByProfessionalExperience(String profissao);
    List<Mentor> findByCategories(Category category);
    List<Mentor> findByCategoryName(String nomeCategoria);
}

