package com.malfaa.pmdp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.malfaa.pmdp.dto.CategoryDTO;
import com.malfaa.pmdp.model.Category;
import org.springframework.stereotype.Service;

import com.malfaa.pmdp.repository.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    /**
     * Função que busca uma categoria dentro do banco de dados utilizando o ID.
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public CategoryDTO searchById(Long id){
        Category categoryFound = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("Categoria não encontrada."));
        return convertToDTO(categoryFound);
    }

    /**
     * Função que busca uma categoria dentro do banco de dados utilizando o nome.
     *
     * @param nome
     * @return
     */
    @Transactional(readOnly = true)
    public CategoryDTO searchByCategoryName(String nome){
        Category categoryFound = repository.findByName(nome)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        return convertToDTO(categoryFound);
    }

    /**
     * Função que busca todas as categorias dentro do banco de dados.
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<CategoryDTO> searchAll(){
        List<Category> listCategory = repository.findAll();
        return listCategory.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    /**
     * Salva uma categoria no banco de dados.
     * 
     * @param category
     * @return repository.save(categoria)
     */
    @Transactional
    public CategoryDTO createCategory(CategoryDTO category){
        Optional<Category> categoriaExistente = repository.findByName(category.name());
        if(categoriaExistente.isPresent()){
            throw new IllegalStateException("Já existe uma categoria com este nome.");
        }

        Category newCategory = new Category();
        newCategory.setName(category.name());
        newCategory.setDescription(category.description());

        Category savedCategory = repository.save(newCategory);

        return convertToDTO(savedCategory);
    }

    /**
     * Remove apenas uma categoria do banco de dados.
     * 
     * @param category
     */
    @Transactional
    public void deleteCategory(Category category){
        repository.delete(category);
    }
    
    /**
     * Remove as categorias que foram listadas do banco de dados.
     * 
     * @param listaCategorias
     */
    @Transactional
    public void deleteCategories(List<Long> listaCategorias){
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
    public CategoryDTO editCategory(Long idCategoria, Category categoryAtualizada){
        Category categoryExist = repository.findById(idCategoria)
                        .orElseThrow(() -> new IllegalStateException("Categoria com ID " + idCategoria + " não encontrada."));
        categoryExist.setName(categoryAtualizada.getName());
        categoryExist.setDescription(categoryAtualizada.getDescription());

        Category savedCategory = repository.save(categoryExist);

        return convertToDTO(savedCategory);
    }

    private CategoryDTO convertToDTO(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}