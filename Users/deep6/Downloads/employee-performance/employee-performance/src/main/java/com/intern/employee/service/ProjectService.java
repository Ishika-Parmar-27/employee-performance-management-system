package com.intern.employee.service;

import com.intern.employee.entity.Project;
import com.intern.employee.exception.ResourceNotFoundException;
import com.intern.employee.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));
    }

   public Project updateProject(Long id, Project project) {

    Project existingProject = getProjectById(id);

    existingProject.setProjectName(
            project.getProjectName());

    existingProject.setDescription(
            project.getDescription());

    existingProject.setDeadline(
            project.getDeadline());

    return projectRepository.save(existingProject);
}
    public void deleteProject(Long id) {

        Project project = getProjectById(id);

        projectRepository.delete(project);
    }
}