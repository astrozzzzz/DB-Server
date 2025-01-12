package ru.lazarev.db_server.controller;

import ru.lazarev.db_server.entity.Project;
import ru.lazarev.db_server.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // Создать новый проект
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    // Получить проект по ID
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // Получить все проекты
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Обновить данные проекта
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setProjectName(projectDetails.getProjectName());
        project.setDescription(projectDetails.getDescription());
        project.setBudget(projectDetails.getBudget());
        project.setDeadline(projectDetails.getDeadline());
        return projectRepository.save(project);
    }

    // Удалить проект
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }
}