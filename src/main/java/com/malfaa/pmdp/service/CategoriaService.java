package com.malfaa.pmdp.service;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.stereotype.Service;

import com.malfaa.pmdp.model.Categoria;
import com.malfaa.pmdp.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository){
        this.repository = repository;
    }

    public Optional<Categoria> buscarCategoria(Long id){return repository.findById(id);};
    public Optional<Categoria> buscarCategoria(String nome){ return repository.findByNome(nome);};
    public List<Categoria> buscarAll(){return repository.findAll();};


    /**
     * Salva uma categoria no banco de dados.
     * 
     * @param categoria
     * @return repository.save(categoria)
     */
    public Categoria criarCategoria(Categoria categoria){
        Optional<Categoria> categoriaExistente = repository.findByNome(categoria.getNome());
        if(categoriaExistente.isPresent()){
            throw new IllegalStateException("Já existe uma categoria com este nome.");
        }
        return repository.save(categoria);
    }

    /**
     * Remove apenas uma categoria do banco de dados.
     * 
     * @param categoria
     */
    public void removerCategoria(Categoria categoria){
        repository.delete(categoria);
    }
    
    /**
     * Remove as categorias que foram listadas do banco de dados.
     * 
     * @param listaCategorias
     */
    public void removerCategorias(List<Long> listaCategorias){
        if(listaCategorias.isEmpty() || listaCategorias == null){
            return;
        }
        repository.deleteAllById(listaCategorias);
    }

    @Transactional
    public Categoria editarCategoria(Long idCategoria, Categoria categoriaAtualizada){
        Categoria categoriaExist = repository.findById(idCategoria)
                        .orElseThrow(() -> new IllegalStateException("Categoria com ID " + idCategoria + " não encontrada."));
        categoriaExist.setNome(categoriaAtualizada.getNome());
        categoriaExist.setDescricao(categoriaAtualizada.getDescricao());

        return repository.save(categoriaExist);
    }
}