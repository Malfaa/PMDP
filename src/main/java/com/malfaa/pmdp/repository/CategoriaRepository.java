package com.malfaa.pmdp.repository;

import com.malfaa.pmdp.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Representa o repositório do modelo Categoria
 * <p>
 * Esta interface é utilizada para fazer as operações do CRUD, utilizando
 * o JpaRepository assim facilitando e agilizando o processo básico
 * de adição e remoção em um repository
 * <p>
 * 
 * @author Malfaa
 * @version 1.0
 */

 @Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
     /**
      * Busca uma categoria pelo seu nome exato.
      * O Spring Data JPA implementará este método automaticamente baseado no nome.
      *
      * @param nome O nome da categoria a ser buscada.
      * @return um Optional contendo a Categoria encontrada, ou um Optional vazio se não encontrar.
      */
    Optional<Categoria> findByName(String nome) ;

}