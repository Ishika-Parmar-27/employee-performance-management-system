package com.intern.product.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.intern.product.entity.Category;
import com.intern.product.entity.Product;
import com.intern.product.repository.CategoryRepository;
import com.intern.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

    @Autowired
    CategoryRepository crepo;

    public Product create(Product product) {

        Long id = product.getCategory().getId();

        Category category = crepo.findById(id).orElse(null);

        product.setCategory(category);

        return repo.save(product);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public List<Product> filter(String name) {
        return repo.findByProductNameContaining(name);
    }

    public Page<Product> pagination(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }
    public List<Product> sortProducts(String field) {
    return repo.findAll(Sort.by(field));
}
}