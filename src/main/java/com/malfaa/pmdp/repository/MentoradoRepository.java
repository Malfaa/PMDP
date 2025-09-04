package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Mentorado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentoradoRepository extends JpaRepository<Mentorado, Long>{
    List<Mentorado> findByInteresses(String interesse);
}
