package com.intern.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.employee.entity.Department;
import com.intern.employee.security.JwtAuthenticationFilter;
import com.intern.employee.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void testGetDepartmentById() throws Exception {

        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("IT");

        when(departmentService.getDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                        .value("IT"));
    }

    @Test
    void testGetAllDepartments() throws Exception {

        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("IT");

        when(departmentService.getAllDepartments())
                .thenReturn(List.of(department));

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateDepartment() throws Exception {

        Department department = new Department();
        department.setDepartmentName("IT");

        when(departmentService.createDepartment(any(Department.class)))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isOk());
    }
}