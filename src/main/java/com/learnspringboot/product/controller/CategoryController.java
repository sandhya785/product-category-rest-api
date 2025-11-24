package com.learnspringboot.product.controller;

import com.learnspringboot.product.DTO.CategoryDTO;

import com.learnspringboot.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "Category REST API CRUD operations",
        description = "CREATE READ UPDATE DELETE operations for Category REST API"
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    //get categories
    @Operation(
            summary = "Fetch all categories",
            description = "REST API to fetch all categoriess along with their products."
    )
    @GetMapping
    @SuppressWarnings("unused")
    public List<CategoryDTO> getCategories(){
        return categoryService.getAllCategories();
    }

    //delete category
    //update category
    @Operation(
            summary = "Create Category",
            description = "REST API to create Category."
    )
    @ApiResponse(responseCode = "201",
    description = "CREATED")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SuppressWarnings("unused")
    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){

            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedCategory);


        //return new ResponseEntity<>(categoryService.createCategory(categoryDTO),HttpStatus.CREATED);
    }
    //get category by id
    @Operation(
            summary = "Fetch Category by Category id",
            description = "REST API to fetch category by category id."
    )
    @SuppressWarnings("unused")
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //delete category
    @Operation(
            summary = "Delete Category by Category id",
            description = "REST API to delete category by category id."
    )
    @SuppressWarnings("unused")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }


}
