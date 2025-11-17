package com.learnspringboot.product.service;

import com.learnspringboot.product.DTO.CategoryDTO;
import com.learnspringboot.product.DTO.ProductDTO;
import com.learnspringboot.product.entity.Category;
import com.learnspringboot.product.entity.Product;
import com.learnspringboot.product.mapper.CategoryMapper;
import com.learnspringboot.product.mapper.ProductMapper;
import com.learnspringboot.product.repository.CategoryRepository;
import com.learnspringboot.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public ProductDTO createProduct(ProductDTO productDTO){
        Category category=categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found"));

        Product product=ProductMapper.toProductEntity(productDTO, category);
        product=productRepository.save(product);
        return ProductMapper.toProductDTO(product);

    }
    //get all products
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }
    //get product by id
    public ProductDTO getProductById(Long id){
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found"));
        return ProductMapper.toProductDTO(product);
    }
    //update product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product product=productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("product not found"));
        Category category=categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("category not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product "+id+" has been deleted";
    }
}


