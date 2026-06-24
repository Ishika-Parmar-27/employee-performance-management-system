package com.intern.employee.controller;

import com.intern.employee.entity.Employee;
import com.intern.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(
            @RequestBody Employee employee) {

        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully";
        
    }
    @PutMapping("/{employeeId}/department/{departmentId}")
public Employee assignDepartment(
        @PathVariable Long employeeId,
        @PathVariable Long departmentId) {

    return employeeService.assignDepartment(employeeId, departmentId);
}
    @GetMapping("/test")
public String test(org.springframework.security.core.Authentication auth) {
    return auth.getAuthorities().toString();
}
@PostMapping("/{employeeId}/projects/{projectId}")
public Employee assignProject(
        @PathVariable Long employeeId,
        @PathVariable Long projectId) {

    return employeeService.assignProject(employeeId, projectId);
}
@GetMapping("/search")
public List<Employee> searchEmployees(
        @RequestParam String name) {

    return employeeService.searchEmployees(name);
}
@GetMapping("/paged")
public Page<Employee> getEmployeesWithPaginationAndSorting(

        @RequestParam int page,
        @RequestParam int size,
        @RequestParam String sortBy) {

    return employeeService
            .getEmployeesWithPaginationAndSorting(
                    page,
                    size,
                    sortBy
            );
}
}