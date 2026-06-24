package com.intern.employee.controller;

import com.intern.employee.entity.Project;
import com.intern.employee.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Project createProject(
            @RequestBody Project project) {

        return projectService.createProject(project);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(
            @PathVariable Long id) {

        return projectService.getProjectById(id);
    }

    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @RequestBody Project project) {

        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return "Project deleted successfully";
    }
}