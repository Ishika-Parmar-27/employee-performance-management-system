package com.intern.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.product.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}