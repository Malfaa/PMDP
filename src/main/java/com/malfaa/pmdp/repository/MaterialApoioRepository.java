package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.MaterialApoio;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.model.Sessao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialApoioRepository extends JpaRepository<MaterialApoio, Long> {
    Optional<String> findByLinkArquivo(String link);
    List<MaterialApoio> findByMentor(Mentor mentor);
    List<MaterialApoio> findBySessao(Sessao sessao);
}
