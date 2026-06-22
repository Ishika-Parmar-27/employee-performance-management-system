package com.intern.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intern.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContaining(String productName);

    @Query("SELECT p.productName, p.soldCount FROM Product p ORDER BY p.soldCount DESC")
    List<Object[]> getTopProducts();
}