package com.intern.employee.dto;

import com.intern.employee.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String designation;
    private Double salary;
    private Role role;
}