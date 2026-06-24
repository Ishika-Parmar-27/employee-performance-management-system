package com.intern.employee.service;
import java.util.Optional;
import static org.mockito.Mockito.*;
import com.intern.employee.entity.Employee;
import com.intern.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetEmployeeById() {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Ishika");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("Ishika", result.getName());

        verify(employeeRepository, times(1))
                .findById(1L);
    }

    @Test
    void testCreateEmployee() {

        Employee employee = new Employee();
        employee.setName("Ishika");

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee savedEmployee =
                employeeService.createEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals("Ishika",
                savedEmployee.getName());

        verify(employeeRepository, times(1))
                .save(employee);
    }

   @Test
void testDeleteEmployee() {

    Long employeeId = 1L;

    Employee employee = new Employee();
    employee.setId(employeeId);
    employee.setName("Ishika");

    when(employeeRepository.findById(employeeId))
            .thenReturn(Optional.of(employee));

    doNothing()
            .when(employeeRepository)
            .delete(employee);

    employeeService.deleteEmployee(employeeId);

    verify(employeeRepository, times(1))
            .findById(employeeId);

    verify(employeeRepository, times(1))
            .delete(employee);
}
}