package ru.lazarev.db_server.controller;

import ru.lazarev.db_server.entity.Employee;
import ru.lazarev.db_server.entity.EmployeeProject;
import ru.lazarev.db_server.entity.EmployeeProjectId;
import ru.lazarev.db_server.entity.Project;
import ru.lazarev.db_server.repository.EmployeeProjectRepository;
import ru.lazarev.db_server.repository.EmployeeRepository;
import ru.lazarev.db_server.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/employee-projects")
public class EmployeeProjectController {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Назначить сотрудника на проект
    @PostMapping
    public EmployeeProject assignEmployeeToProject(@RequestBody Map<String, Object> requestBody) {
        UUID employeeId = UUID.fromString(requestBody.get("employeeId").toString());
        Long projectId = Long.parseLong(requestBody.get("projectId").toString());

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));

        EmployeeProjectId employeeProjectId = new EmployeeProjectId();
        employeeProjectId.setEmployeeId(employeeId);
        employeeProjectId.setProjectId(projectId);

        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setId(employeeProjectId);
        employeeProject.setEmployee(employee);
        employeeProject.setProject(project);

        return employeeProjectRepository.save(employeeProject);
    }

    @PutMapping
    public EmployeeProject updateEmployeeProject(@RequestBody Map<String, Object> requestBody) {
        UUID oldEmployeeId = UUID.fromString(requestBody.get("oldEmployeeId").toString());
        Long oldProjectId = Long.parseLong(requestBody.get("oldProjectId").toString());
        UUID newEmployeeId = UUID.fromString(requestBody.get("newEmployeeId").toString());
        Long newProjectId = Long.parseLong(requestBody.get("newProjectId").toString());

        // Удаляем старую связь
        EmployeeProject existingEmployeeProject = employeeProjectRepository.findByEmployeeIdAndProjectId(oldEmployeeId, oldProjectId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        employeeProjectRepository.delete(existingEmployeeProject);

        // Создаём новую связь
        Employee newEmployee = employeeRepository.findById(newEmployeeId).orElseThrow(() -> new RuntimeException("New employee not found"));
        Project newProject = projectRepository.findById(newProjectId).orElseThrow(() -> new RuntimeException("New project not found"));

        EmployeeProjectId newEmployeeProjectId = new EmployeeProjectId();
        newEmployeeProjectId.setEmployeeId(newEmployeeId);
        newEmployeeProjectId.setProjectId(newProjectId);

        EmployeeProject newEmployeeProject = new EmployeeProject();
        newEmployeeProject.setId(newEmployeeProjectId);
        newEmployeeProject.setEmployee(newEmployee);
        newEmployeeProject.setProject(newProject);

        return employeeProjectRepository.save(newEmployeeProject);
    }


    // Удалить сотрудника из проекта
    @DeleteMapping
    public void removeEmployeeFromProject(@RequestParam UUID employeeId, @RequestParam Long projectId) {
        EmployeeProject employeeProject = employeeProjectRepository.findByEmployeeIdAndProjectId(employeeId, projectId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        employeeProjectRepository.delete(employeeProject);
    }

    // Получить проекты для сотрудника
    @GetMapping("/employees/{employeeId}/projects")
    public List<Project> getProjectsByEmployeeId(@PathVariable UUID employeeId) {
        return employeeProjectRepository.findProjectsByEmployeeId(employeeId);
    }

    // Получить сотрудников для проекта
    @GetMapping("/projects/{projectId}/employees")
    public List<Employee> getEmployeesByProjectId(@PathVariable Long projectId) {
        return employeeProjectRepository.findEmployeesByProjectId(projectId);
    }
}