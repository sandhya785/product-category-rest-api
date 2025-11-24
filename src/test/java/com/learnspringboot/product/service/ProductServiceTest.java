package com.learnspringboot.product.service;

import com.learnspringboot.product.DTO.ProductDTO;
import com.learnspringboot.product.entity.Category;
import com.learnspringboot.product.entity.Product;
import com.learnspringboot.product.exception.CategoryNotFoundException;
import com.learnspringboot.product.repository.CategoryRepository;
import com.learnspringboot.product.repository.ProductRepository;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Category category;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("A high-end gaming laptop");
        product.setPrice(1500.00);
        product.setCategory(category);

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Laptop");
        productDTO.setDescription("A high-end gaming laptop");
        productDTO.setPrice(1500.00);
        productDTO.setCategoryId(1L);
    }

    @AfterEach
    void tearDown() {
        reset(productRepository);
        reset(categoryRepository);
    }
    @Test
    void testCreateProduct() {
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO createdProduct = productService.createProduct(productDTO);

        assertNotNull(createdProduct);
        assertEquals(productDTO.getName(), createdProduct.getName());
        verify(categoryRepository, times(1)).findById(any(Long.class));
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testCreateProductCategoryNotFound() {
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> productService.createProduct(productDTO));
        verify(categoryRepository, times(1)).findById(any(Long.class));
        verify(productRepository, never()).save(any(Product.class));
    }

//    @Test
//    void testGetAllProducts() {
//        when(productRepository.findAll()).thenReturn(List.of(product));
//
//        List<ProductDTO> products = productService.getAllProducts();
//
//        assertFalse(products.isEmpty());
//        assertEquals(1, products.size());
//        verify(productRepository, times(1)).findAll();
//    }
@Test
void testGetAllProducts() {
    Page<Product> productPage = new PageImpl<>(List.of(product));
    when(productRepository.findAll(PageRequest.of(0, 5, Sort.by("id").ascending())))
            .thenReturn(productPage);

    Page<ProductDTO> products = productService.getAllProducts(0, 5, "id", "asc");

    assertNotNull(products);
    assertEquals(1, products.getContent().size());
    assertEquals(product.getName(), products.getContent().getFirst().getName());

    verify(productRepository, times(1)).findAll(PageRequest.of(0, 10, Sort.by("id").ascending()));
}


    @Test
    void testGetProductById() {
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));

        ProductDTO foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals(product.getName(), foundProduct.getName());
        verify(productRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO updatedProduct = productService.updateProduct(1L, productDTO);

        assertNotNull(updatedProduct);
        assertEquals(productDTO.getName(), updatedProduct.getName());
        verify(productRepository, times(1)).findById(any(Long.class));
        verify(categoryRepository, times(1)).findById(any(Long.class));
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(any(Long.class));

        String result = productService.deleteProduct(1L);

        assertEquals("Product 1 has been deleted!", result);
        verify(productRepository, times(1)).deleteById(any(Long.class));
    }
}