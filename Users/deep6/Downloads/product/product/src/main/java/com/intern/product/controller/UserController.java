package com.intern.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.intern.product.config.JwtUtil;
import com.intern.product.dto.LoginRequest;
import com.intern.product.entity.Role;
import com.intern.product.entity.User;
import com.intern.product.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        user.setRole(Role.USER); // default role

        return service.register(user);
    }

    // LOGIN (JWT TOKEN RETURN)
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = service.login(request.getUsername());

        if (user != null &&
            encoder.matches(request.getPassword(), user.getPassword())) {

            return jwtUtil.generateToken(user.getUsername());
        }

        return "Invalid username or password";
    }

    // TEST ENDPOINT
    @GetMapping("/test")
    public String test() {
        return "Auth Controller Working";
    }
}