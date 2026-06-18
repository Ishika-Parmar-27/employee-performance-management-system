package com.intern.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.intern.product.entity.Category;
import com.intern.product.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    @PostMapping("/create")
    public Category create(@Valid @RequestBody Category category) {
        return service.create(category);
    }

    @GetMapping("/getAll")
    public List<Category> getAll() {
        return service.getAll();
    }
}