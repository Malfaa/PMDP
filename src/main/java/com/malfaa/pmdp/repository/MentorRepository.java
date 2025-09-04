package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Categoria;
import com.malfaa.pmdp.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByFormacaoAcademica(String formacao);
    List<Mentor> findByExperienciaProfissional(String profissao);
    List<Mentor> findByCategorias(Categoria categoria);
    List<Mentor> findByCategoria_Nome(String nomeCategoria);
}

