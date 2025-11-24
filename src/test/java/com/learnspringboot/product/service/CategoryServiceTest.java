package com.learnspringboot.product.service;

import com.learnspringboot.product.DTO.CategoryDTO;
import com.learnspringboot.product.entity.Category;
import com.learnspringboot.product.exception.CategoryAlreadyExistsException;
import com.learnspringboot.product.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private CategoryDTO categoryDTO;



    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("test");
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCategory_categoryShouldbeCreated() {
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        assertNotNull(savedCategory);
        assertEquals(categoryDTO.getName(), savedCategory.getName());
    }

    @Test
    void createCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.of(category));
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.createCategory(categoryDTO));
        verify(categoryRepository, times(0)).save(any(Category.class));
    }
}