package com.learnspringboot.product.controller;

import com.learnspringboot.product.DTO.ProductDTO;
import com.learnspringboot.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Product REST API CRUD operations",
        description = "CREATE READ UPDATE DELETE operations for Product REST API"
)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    //get all products
    @Operation(
            summary = "Fetch all products",
            description = "REST API to fetch all products."
    )
    @GetMapping
//    public List<ProductDTO> getAllProducts(){
//        return productService.getAllProducts();
//    }
    public Page<ProductDTO> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction)
    {
        return productService.getAllProducts(page, size, sortBy, direction);
    }
    //get product id
    @Operation(
            summary = "Fetch product by product id",
            description = "REST API to fetch product by product id."
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){

        return productService.getProductById(id);
    }
    //delete product
    @Operation(
            summary = "Delete product by product id",
            description = "REST API to delete product by product id."
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
    //update product
    @Operation(
            summary = "Update product by product id",
            description = "REST API to update product by product id."
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }

    //create product
    @Operation(
            summary = "Create product",
            description = "REST API to create product."
    )
    @ApiResponse(responseCode = "201",
            description = "CREATED")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        ProductDTO createProduct= productService.createProduct(productDTO);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }

}
