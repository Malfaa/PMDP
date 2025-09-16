package com.malfaa.pmdp.service;

import com.malfaa.pmdp.dto.CategoryDTO;
import com.malfaa.pmdp.model.Category;
import com.malfaa.pmdp.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void searchById() {
    }

    @Test
    void searchByCategoryName() {
    }

    @Test
    void searchAll() {
    }

    /**
     * createCategory
     */
    @Test
    void deveCriarCategoriaComSucessoQuandoNomeNaoExiste() {
        CategoryDTO inputDTO = new CategoryDTO(null, "Tecnologia", "Teste");

        Category categoryParaSalvar = new Category();
        categoryParaSalvar.setName(inputDTO.name());
        categoryParaSalvar.setDescription(inputDTO.description());

        Category categorySalva = new Category();
        categorySalva.setId(1L);
        categorySalva.setName(inputDTO.name());
        categorySalva.setDescription(inputDTO.description());

        when(categoryRepository.findByName("Tecnologia")).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(categorySalva);

        CategoryDTO resultDTO = categoryService.createCategory(inputDTO);

        assertNotNull(resultDTO);
        assertEquals(1L, resultDTO.id());
        assertEquals("Tecnologia", resultDTO.name());

        verify(categoryRepository, times(1)).findByName("Tecnologia");
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void deleteCategories() {
    }

    @Test
    void editCategory() {
    }
}