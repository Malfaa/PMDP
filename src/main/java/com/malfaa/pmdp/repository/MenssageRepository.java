package com.malfaa.pmdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malfaa.pmdp.model.Message;

@Repository
public interface MenssageRepository extends JpaRepository<Message, Long> {
    
}
