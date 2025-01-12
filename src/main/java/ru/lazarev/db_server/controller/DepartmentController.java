package ru.lazarev.db_server.controller;

import ru.lazarev.db_server.entity.Department;
import ru.lazarev.db_server.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Создать новый департамент
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    // Получить департамент по ID
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // Получить все департаменты
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Обновить департамент
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        department.setBudget(departmentDetails.getBudget());
        department.setIsActive(departmentDetails.getIsActive());
        department.setIpAddress(departmentDetails.getIpAddress());
        return departmentRepository.save(department);
    }

    // Удалить департамент
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }
}