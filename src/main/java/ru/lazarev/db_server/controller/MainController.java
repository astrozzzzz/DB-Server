package ru.lazarev.db_server.controller;

import org.springframework.stereotype.Controller;
import ru.lazarev.db_server.entity.Department;
import ru.lazarev.db_server.entity.Employee;
import ru.lazarev.db_server.entity.EmployeeProject;
import ru.lazarev.db_server.entity.Project;
import ru.lazarev.db_server.repository.DepartmentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.lazarev.db_server.repository.EmployeeProjectRepository;
import ru.lazarev.db_server.repository.EmployeeRepository;
import ru.lazarev.db_server.repository.ProjectRepository;

import java.util.*;

@Controller
@RequestMapping("/")
@Tag(name = "Main", description = "Панель управления")
public class MainController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @GetMapping
    public String index(Model model) {
        List<Department> departments = departmentRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Department::getDepartmentId))
                .toList();

        List<Project> projects = projectRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Project::getProjectId))
                .toList();

        List<Employee> employees = employeeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Employee::getEmployeeId))
                .toList();

        List<EmployeeProject> projectEmployeePairs = employeeProjectRepository.findAll()
                .stream()
                .toList();

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("projects", projects);
        model.addAttribute("employeeProjectPairs", projectEmployeePairs);
        return "index";
    }

    @GetMapping("/add-department")
    public String addDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "add-department";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        model.addAttribute("department", new Department());
        return "add-employee";
    }

    @GetMapping("/edit-department/{id}")
    public String editDepartment(Model model, @PathVariable Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
        model.addAttribute("department", department);
        return "edit-department";
    }

    @GetMapping("/edit-employee/{id}")
    public String editEmployee(Model model, @PathVariable UUID id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "edit-employee";
    }

    @GetMapping("/add-project")
    public String addProject(Model model) {
        model.addAttribute("project", new Project());
        return "add-project";
    }

    @GetMapping("/edit-project/{id}")
    public String editProject(Model model, @PathVariable Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + id));
        model.addAttribute("project", project);
        return "edit-project";
    }

    @GetMapping("/add-employee-project")
    public String addEmployeeProject(Model model) {
//        model.addAttribute("employee_project", new EmployeeProject());
        return "add-employee-project";
    }

    @GetMapping("/edit-employee-project/{project_id}/{employee_id}")
    public String editProject(Model model, @PathVariable Long project_id, @PathVariable UUID employee_id) {
        Project project = projectRepository.findById(project_id).orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + project_id));
        Employee employee = employeeRepository.findById(employee_id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + employee_id));
        model.addAttribute("project", project);
        model.addAttribute("employee", employee);
        return "edit-employee-project";
    }
}
