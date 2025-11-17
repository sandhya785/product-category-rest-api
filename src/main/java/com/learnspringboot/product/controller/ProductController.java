package com.learnspringboot.product.controller;

import com.learnspringboot.product.DTO.ProductDTO;
import com.learnspringboot.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    //get all products
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    //get product id
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){

        return productService.getProductById(id);
    }
    //delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
    //update product
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }

    //create product

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createProduct= productService.createProduct(productDTO);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }

}
