package com.malfaa.pmdp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malfaa.pmdp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}