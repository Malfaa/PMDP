package com.malfaa.pmdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malfaa.pmdp.model.Pagamento;
import com.malfaa.pmdp.model.Sessao;
import com.malfaa.pmdp.model.Mentorado;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
    List<Pagamento> findBySessao(Sessao sessao);
    List<Pagamento> findByMentorado(Mentorado mentorado);


}
