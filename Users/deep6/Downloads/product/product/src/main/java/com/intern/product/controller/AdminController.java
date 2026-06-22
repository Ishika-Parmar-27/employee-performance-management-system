package com.intern.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.intern.product.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }
    @GetMapping("/monthly-sales")
public ResponseEntity<?> monthlySales() {
    return ResponseEntity.ok(adminService.getMonthlySales());
}

@GetMapping("/top-products")
public ResponseEntity<?> topProducts() {
    return ResponseEntity.ok(adminService.getTopProducts());
}
}