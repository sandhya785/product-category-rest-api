package com.learnspringboot.product.controller;

import com.learnspringboot.product.DTO.CategoryDTO;
import com.learnspringboot.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    //get categories
    @GetMapping
    public List<CategoryDTO> getCategories(){
        return categoryService.getAllCategories();
    }

    //delete category
    //update category

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO),HttpStatus.CREATED);
    }
    //get category by id
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    // delete category
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }


}
