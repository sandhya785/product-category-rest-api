package com.learnspringboot.product.mapper;

import com.learnspringboot.product.DTO.ProductDTO;
import com.learnspringboot.product.entity.Product;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product){
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId());
    }
    public static Product toProductEntity(ProductDTO productDTO){
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }


}
