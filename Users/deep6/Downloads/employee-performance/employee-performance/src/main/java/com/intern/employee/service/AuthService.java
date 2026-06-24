package com.intern.employee.service;

import com.intern.employee.dto.AuthResponse;
import com.intern.employee.dto.LoginRequest;
import com.intern.employee.dto.RegisterRequest;
import com.intern.employee.entity.Employee;
import com.intern.employee.repository.EmployeeRepository;
import com.intern.employee.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        employee.setRole(request.getRole());

        employeeRepository.save(employee);

        return "Employee Registered Successfully";
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token =
                jwtService.generateToken(request.getEmail());

        return new AuthResponse(token);
    }
}