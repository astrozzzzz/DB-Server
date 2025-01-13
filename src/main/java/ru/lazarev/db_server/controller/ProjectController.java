package ru.lazarev.db_server.controller;

import ru.lazarev.db_server.entity.Project;
import ru.lazarev.db_server.repository.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@Tag(name = "Project", description = "API для управления проектами")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Operation(summary = "Создать новый проект",
            description = "Создает новый проект и сохраняет его в базе данных")
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @Operation(summary = "Получить проект по ID",
            description = "Возвращает информацию о проекте по его уникальному идентификатору")
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Operation(summary = "Получить список всех проектов",
            description = "Возвращает список всех проектов")
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Operation(summary = "Обновить данные проекта",
            description = "Обновляет данные проекта по его ID")
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setProjectName(projectDetails.getProjectName());
        project.setDescription(projectDetails.getDescription());
        project.setBudget(projectDetails.getBudget());
        project.setDeadline(projectDetails.getDeadline());
        return projectRepository.save(project);
    }

    @Operation(summary = "Удалить проект",
            description = "Удаляет проект из базы данных по его ID")
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }
}
