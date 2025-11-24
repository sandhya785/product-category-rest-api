package com.learnspringboot.product.repository;

import com.learnspringboot.product.entity.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CategoryRepositoryTest {


    private final CategoryRepository categoryRepository;

    private Category category;
    @Autowired
    public CategoryRepositoryTest(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @BeforeEach
    void setUp() {
        // insert row in category table
        category = new Category();
        category.setName("test");
        categoryRepository.save(category);
    }

    @AfterEach
    void tearDown() {
        // delete row from category table
        categoryRepository.delete(category);
    }

    @Test
    void findByName() {
        Category foundCategory = categoryRepository.findByName("test").orElse(null);
        assertNotNull(foundCategory);
        assertEquals(category.getName(), foundCategory.getName());
    }

    @Test
    void deleteByName() {
        categoryRepository.deleteByName("test");
        Category foundCategory = categoryRepository.findByName("test").orElse(null);
        assertNull(foundCategory);
    }
}