package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.SupportingMaterial;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.model.Session;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportingMaterialRepository extends JpaRepository<SupportingMaterial, Long> {
    Optional<String> findByUrlFile(String link);
    List<SupportingMaterial> findByMentor(Mentor mentor);
    List<SupportingMaterial> findBySessao(Session session);
}
