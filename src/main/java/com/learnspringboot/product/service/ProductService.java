package com.learnspringboot.product.service;
import com.learnspringboot.product.DTO.ProductDTO;
import com.learnspringboot.product.entity.Category;
import com.learnspringboot.product.entity.Product;
import com.learnspringboot.product.exception.CategoryNotFoundException;

import com.learnspringboot.product.mapper.ProductMapper;
import com.learnspringboot.product.repository.CategoryRepository;
import com.learnspringboot.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public ProductDTO createProduct(ProductDTO productDTO){
        Category category=categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new CategoryNotFoundException("Category not found"));

        Product product=ProductMapper.toProductEntity(productDTO, category);
        product=productRepository.save(product);
        return ProductMapper.toProductDTO(product);

    }
    //get all products
    //   public List<ProductDTO> getAllProducts(){
    //        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    //    }
    public Page<ProductDTO> getAllProducts(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(pageable)
                .map(ProductMapper::toProductDTO);
    }
    //get product by id
    public ProductDTO getProductById(Long id){
        Product product=productRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found"));
        return ProductMapper.toProductDTO(product);
    }
    //update product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product product=productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("product not found"));
        Category category=categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new CategoryNotFoundException("category id "+productDTO.getCategoryId()+" not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product "+id+" has been deleted!";
    }
}


