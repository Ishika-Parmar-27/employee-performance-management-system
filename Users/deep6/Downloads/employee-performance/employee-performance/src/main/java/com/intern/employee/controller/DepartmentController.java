package com.intern.employee.controller;

import com.intern.employee.entity.Department;
import com.intern.employee.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public Department createDepartment(
            @RequestBody Department department) {

        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(
            @PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return "Department deleted successfully";
    }
}