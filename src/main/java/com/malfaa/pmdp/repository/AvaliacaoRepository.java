package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Avaliacao;
import com.malfaa.pmdp.model.Mentorado;
import com.malfaa.pmdp.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByMentorado(Mentorado mentorado);
    Optional<Avaliacao> findBySessao(Sessao sessao);
    List<Avaliacao> findByNota(Integer nota);
}
