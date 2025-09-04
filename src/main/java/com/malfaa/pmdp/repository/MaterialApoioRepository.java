package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.MaterialApoio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialApoioRepository extends JpaRepository<MaterialApoio, Long> {

}
