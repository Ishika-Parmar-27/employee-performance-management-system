package com.intern.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.employee.entity.Employee;
import com.intern.employee.security.JwtAuthenticationFilter;
import com.intern.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void testGetEmployeeById() throws Exception {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Ishika");

        when(employeeService.getEmployeeById(1L))
                .thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Ishika"));
    }

    @Test
    void testGetAllEmployees() throws Exception {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Ishika");

        when(employeeService.getAllEmployees())
                .thenReturn(List.of(employee));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name")
                        .value("Ishika"));
    }

    @Test
    void testCreateEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Ishika");

        when(employeeService.createEmployee(any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Ishika"));
    }

    @Test
    void testDeleteEmployee() throws Exception {

        doNothing().when(employeeService)
                .deleteEmployee(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("Employee deleted successfully"));
    }
}