package com.intern.employee.service;

import com.intern.employee.entity.Project;
import com.intern.employee.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void testGetProjectById() {

        Project project = new Project();
        project.setId(1L);
        project.setProjectName("Employee Portal");

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        Project result =
                projectService.getProjectById(1L);

        assertNotNull(result);
        assertEquals("Employee Portal",
                result.getProjectName());
    }

    @Test
    void testCreateProject() {

        Project project = new Project();
        project.setProjectName("Employee Portal");

        when(projectRepository.save(project))
                .thenReturn(project);

        Project saved =
                projectService.createProject(project);

        assertNotNull(saved);
    }

    @Test
    void testDeleteProject() {

        Project project = new Project();
        project.setId(1L);

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        doNothing()
                .when(projectRepository)
                .delete(project);

        projectService.deleteProject(1L);

        verify(projectRepository)
                .delete(project);
    }
}