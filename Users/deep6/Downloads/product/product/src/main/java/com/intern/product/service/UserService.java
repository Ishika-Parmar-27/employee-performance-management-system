package com.intern.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.intern.product.entity.Role;
import com.intern.product.entity.User;
import com.intern.product.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // REGISTER USER
    public User register(User user) {

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // default role
        user.setRole(Role.USER);

        return repo.save(user);
    }

    // LOGIN USER
    public User login(String username) {

        return repo.findByUsername(username)
                .orElse(null);
    }
}