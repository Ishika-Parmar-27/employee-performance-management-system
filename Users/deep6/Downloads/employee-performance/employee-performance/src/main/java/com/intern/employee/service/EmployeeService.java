package com.intern.employee.service;

import com.intern.employee.entity.Department;
import com.intern.employee.entity.Employee;
import com.intern.employee.entity.Project;
import com.intern.employee.exception.ResourceNotFoundException;
import com.intern.employee.repository.DepartmentRepository;
import com.intern.employee.repository.EmployeeRepository;
import com.intern.employee.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;

    public Employee createEmployee(Employee employee) {

        logger.info("Creating employee: {}", employee.getEmail());

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {

        logger.info("Fetching all employees");

        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {

        logger.info("Fetching employee with id: {}", id);

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));
    }

    public Employee updateEmployee(Long id, Employee employee) {

        logger.info("Updating employee with id: {}", id);

        Employee existingEmployee = getEmployeeById(id);

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setRole(employee.getRole());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {

        logger.info("Deleting employee with id: {}", id);

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);
    }

    public Employee assignDepartment(Long employeeId, Long departmentId) {

        logger.info(
                "Assigning department {} to employee {}",
                departmentId,
                employeeId
        );

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public Employee assignProject(Long employeeId, Long projectId) {

        logger.info(
                "Assigning project {} to employee {}",
                projectId,
                employeeId
        );

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        employee.getProjects().add(project);

        return employeeRepository.save(employee);
    }

    public List<Employee> searchEmployees(String name) {

        logger.info("Searching employees with name: {}", name);

        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public Page<Employee> getEmployeesWithPaginationAndSorting(
            int page,
            int size,
            String sortBy) {

        logger.info(
                "Fetching employees with pagination. Page: {}, Size: {}, Sort By: {}",
                page,
                size,
                sortBy
        );

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy)
        );

        return employeeRepository.findAll(pageable);
    }
}