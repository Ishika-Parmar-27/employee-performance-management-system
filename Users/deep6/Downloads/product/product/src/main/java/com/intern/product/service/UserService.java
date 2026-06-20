package com.intern.product.service;

import java.util.UUID;

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

    @Autowired
    private EmailService emailService;

    // REGISTER USER
    public User register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);

        return repo.save(user);
    }

    // LOGIN USER
    public User login(String username) {

        return repo.findByUsername(username)
                .orElse(null);
    }

    // FORGOT PASSWORD
    public String forgotPassword(String email) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);
        user.setTokenExpiry(System.currentTimeMillis() + 300000);

        repo.save(user);

        emailService.sendEmail(email, token);

        return "Password reset email sent successfully";
    }

    // RESET PASSWORD
    public String resetPassword(String token, String newPassword) {

        User user = repo.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (System.currentTimeMillis() > user.getTokenExpiry()) {
            throw new RuntimeException("Token expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setTokenExpiry(null);

        repo.save(user);

        return "Password updated successfully";
    }
}