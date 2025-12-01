package com.learnspringboot.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.learnspringboot.product.entity.Category;
import com.learnspringboot.product.entity.Product;
import com.learnspringboot.product.entity.User;
import com.learnspringboot.product.repository.CategoryRepository;
import com.learnspringboot.product.repository.ProductRepository;
import com.learnspringboot.product.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            // Create categories
            Category c1 = categoryRepository.save(new Category(null, "Electronics", null));
            Category c2 = categoryRepository.save(new Category(null, "Books", null));

            // Create products linked to categories
            productRepository.save(new Product(null, "Laptop", "High performance", 40000.0, c1));
            productRepository.save(new Product(null, "Novel", "Fiction book", 200.0, c2));

            // Create users
            userRepository.save(new User(null, "admin", "admin"));
            userRepository.save(new User(null, "seller", "seller"));
        }
    }
}
