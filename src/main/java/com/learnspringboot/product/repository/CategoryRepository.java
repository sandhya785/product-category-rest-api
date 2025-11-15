package com.learnspringboot.product.repository;

import com.learnspringboot.product.entity.Category;
import com.learnspringboot.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
