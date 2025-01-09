package ru.lazarev.db_server.service;

import ru.lazarev.db_server.entity.Employee;
import ru.lazarev.db_server.entity.EmployeeProject;
import ru.lazarev.db_server.entity.Project;
import ru.lazarev.db_server.repository.EmployeeProjectRepository;
import ru.lazarev.db_server.repository.EmployeeRepository;
import ru.lazarev.db_server.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeProjectService {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void assignEmployeeToProject(UUID employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));

        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setEmployee(employee);
        employeeProject.setProject(project);
        employeeProjectRepository.save(employeeProject);
    }

    public void removeEmployeeFromProject(UUID employeeId, Long projectId) {
        EmployeeProject employeeProject = employeeProjectRepository.findByEmployeeIdAndProjectId(employeeId, projectId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        employeeProjectRepository.delete(employeeProject);
    }

    public List<Project> getProjectsByEmployeeId(UUID employeeId) {
        return employeeProjectRepository.findProjectsByEmployeeId(employeeId);
    }

    public List<Employee> getEmployeesByProjectId(Long projectId) {
        return employeeProjectRepository.findEmployeesByProjectId(projectId);
    }
}