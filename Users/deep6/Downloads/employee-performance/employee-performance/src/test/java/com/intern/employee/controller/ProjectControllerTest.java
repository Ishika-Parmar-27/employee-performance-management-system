package com.intern.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.employee.entity.Project;
import com.intern.employee.security.JwtAuthenticationFilter;
import com.intern.employee.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void testGetProjectById() throws Exception {

        Project project = new Project();
        project.setId(1L);
        project.setProjectName("Employee Portal");

        when(projectService.getProjectById(1L))
                .thenReturn(project);

        mockMvc.perform(get("/projects/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllProjects() throws Exception {

        when(projectService.getAllProjects())
                .thenReturn(List.of(new Project()));

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateProject() throws Exception {

        Project project = new Project();
        project.setProjectName("Employee Portal");

        when(projectService.createProject(any(Project.class)))
                .thenReturn(project);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk());
    }
}