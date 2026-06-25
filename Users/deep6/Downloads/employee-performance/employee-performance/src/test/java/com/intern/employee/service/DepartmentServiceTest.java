package com.intern.employee.service;

import com.intern.employee.entity.Department;
import com.intern.employee.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void testGetDepartmentById() {

        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("IT");

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        Department result =
                departmentService.getDepartmentById(1L);

        assertNotNull(result);
        assertEquals("IT", result.getDepartmentName());
    }

    @Test
    void testCreateDepartment() {

        Department department = new Department();
        department.setDepartmentName("IT");

        when(departmentRepository.save(department))
                .thenReturn(department);

        Department saved =
                departmentService.createDepartment(department);

        assertNotNull(saved);
        assertEquals("IT", saved.getDepartmentName());
    }

    @Test
    void testDeleteDepartment() {

        Department department = new Department();
        department.setId(1L);

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        doNothing().when(departmentRepository)
                .delete(department);

        departmentService.deleteDepartment(1L);

        verify(departmentRepository).delete(department);
    }
}