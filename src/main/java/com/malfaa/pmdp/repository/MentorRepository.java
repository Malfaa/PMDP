package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Category;
import com.malfaa.pmdp.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByAcademicFormationContainingIgnoreCase(String term);
    List<Mentor> findByProfessionalExperienceContainingIgnoreCase(String term);
    List<Mentor> findByCategoriesIn(Set<Category> categories);
    List<Mentor> findByCategories_Name(String categoryName);
}

