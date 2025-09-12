package com.malfaa.pmdp.service;

import java.util.List;
import java.util.Optional;

import com.malfaa.pmdp.model.Category;
import org.springframework.stereotype.Service;

import com.malfaa.pmdp.repository.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoriaService {
    private final CategoryRepository repository;

    public CategoriaService(CategoryRepository repository){
        this.repository = repository;
    }

    /**
     * Função que busca uma categoria dentro do banco de dados utilizando o ID.
     *
     * @param id
     * @return
     */
    public Optional<Category> buscarCategoria(Long id){return repository.findById(id);}

    /**
     * Função que busca uma categoria dentro do banco de dados utilizando o nome.
     *
     * @param nome
     * @return
     */
    public Optional<Category> buscarCategoria(String nome){ return repository.findByName(nome);}

    /**
     * Função que busca todas as categorias dentro do banco de dados.
     *
     * @return
     */
    public List<Category> buscarAll(){return repository.findAll();};


    /**
     * Salva uma categoria no banco de dados.
     * 
     * @param category
     * @return repository.save(categoria)
     */
    public Category criarCategoria(Category category){
        Optional<Category> categoriaExistente = repository.findByName(category.getName());
        if(categoriaExistente.isPresent()){
            throw new IllegalStateException("Já existe uma categoria com este nome.");
        }
        return repository.save(category);
    }

    /**
     * Remove apenas uma categoria do banco de dados.
     * 
     * @param category
     */
    public void deletarCategoria(Category category){
        repository.delete(category);
    }
    
    /**
     * Remove as categorias que foram listadas do banco de dados.
     * 
     * @param listaCategorias
     */
    public void deletarCategorias(List<Long> listaCategorias){
        if(listaCategorias.isEmpty() || listaCategorias == null){
            return;
        }
        repository.deleteAllById(listaCategorias);
    }

    /**
     * Edita uma categoria, utilizando o id da que será alterada para o objeto Categoria novo.
     *
     * @param idCategoria
     * @param categoryAtualizada
     * @return salva uma nova categoria
     */
    @Transactional
    public Category editarCategoria(Long idCategoria, Category categoryAtualizada){
        Category categoryExist = repository.findById(idCategoria)
                        .orElseThrow(() -> new IllegalStateException("Categoria com ID " + idCategoria + " não encontrada."));
        categoryExist.setName(categoryAtualizada.getName());
        categoryExist.setDescription(categoryAtualizada.getDescription());

        return repository.save(categoryExist);
    }
}