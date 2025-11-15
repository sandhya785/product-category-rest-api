package com.learnspringboot.product.service;

import com.learnspringboot.product.DTO.CategoryDTO;
import com.learnspringboot.product.DTO.ProductDTO;
import com.learnspringboot.product.entity.Category;
import com.learnspringboot.product.entity.Product;
import com.learnspringboot.product.mapper.CategoryMapper;
import com.learnspringboot.product.mapper.ProductMapper;
import com.learnspringboot.product.repository.CategoryRepository;
import com.learnspringboot.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;
    public ProductDTO createProduct(ProductDTO productDTO){
        Product product= ProductMapper.toProductEntity(productDTO);
        product=productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

}


