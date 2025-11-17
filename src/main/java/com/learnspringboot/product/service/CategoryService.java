package com.learnspringboot.product.service;

import com.learnspringboot.product.DTO.CategoryDTO;
import com.learnspringboot.product.entity.Category;
import com.learnspringboot.product.mapper.CategoryMapper;
import com.learnspringboot.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper:: toCategoryDTO).toList();
    }
    //get category by id
    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toCategoryDTO(category);
    }
    //delete category
    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "Category " + id+" has been deleted";
    }

}
