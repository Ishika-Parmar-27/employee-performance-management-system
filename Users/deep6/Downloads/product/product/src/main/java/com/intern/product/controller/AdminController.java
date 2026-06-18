package com.intern.product.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome Admin Dashboard 🚀";
    }

    @GetMapping("/users")
    public String viewUsers() {
        return "Only Admin can view all users (dummy response)";
    }
}