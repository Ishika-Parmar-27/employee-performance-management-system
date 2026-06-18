package com.intern.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.product.entity.Category;
import com.intern.product.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repo;

    public Category create(Category c) {
        return repo.save(c);
    }

    public List<Category> getAll() {
        return repo.findAll();
    }
}