package com.intern.employee.service;

import com.intern.employee.entity.Department;
import com.intern.employee.exception.ResourceNotFoundException;
import com.intern.employee.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));
    }

    public Department updateDepartment(Long id, Department department) {

        Department existingDepartment = getDepartmentById(id);

        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setLocation(
            department.getLocation());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {

        Department department = getDepartmentById(id);

        departmentRepository.delete(department);
    }
}