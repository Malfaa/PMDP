package com.malfaa.pmdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malfaa.pmdp.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    
}
