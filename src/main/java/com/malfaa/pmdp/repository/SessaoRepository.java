package com.malfaa.pmdp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malfaa.pmdp.model.Calendario;
import com.malfaa.pmdp.model.Categoria;
import com.malfaa.pmdp.model.Mentor;
import com.malfaa.pmdp.model.Sessao;
import com.malfaa.pmdp.model.enums.Agendamento;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    List<Sessao> findByMentor(Mentor mentor);
    List<Sessao> findByCategoria(Categoria categoria);
    List<Sessao> findByStatus(Agendamento status);
    List<Sessao> findByDate(Calendario calendario);
}