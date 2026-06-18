package com.intern.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.intern.product.entity.Product;
import com.intern.product.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/create")
    public Product create(@Valid @RequestBody Product product) {
        return service.create(product);
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/filter")
    public List<Product> filter(@RequestParam String name) {
        return service.filter(name);
    }

    @GetMapping("/page")
    public Page<Product> page(@RequestParam int page,
                              @RequestParam int size) {
        return service.pagination(page, size);
    }
}