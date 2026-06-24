package com.intern.employee.dto;

import com.intern.employee.entity.Role;
import lombok.Data;

@Data
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private String designation;
    private Double salary;
    private Role role;
}