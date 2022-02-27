package com.csc366.project.repository;

import com.csc366.project.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findBySku(String sku);
}
