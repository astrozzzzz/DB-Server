package ru.lazarev.db_server.controller;

import ru.lazarev.db_server.entity.Department;
import ru.lazarev.db_server.repository.DepartmentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@Tag(name = "Department", description = "API для управления департаментами")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Operation(summary = "Создать новый департамент",
            description = "Создает новый департамент и сохраняет его в базе данных")
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @Operation(summary = "Получить департамент по ID",
            description = "Возвращает информацию о департаменте по его уникальному идентификатору")
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Operation(summary = "Получить все департаменты",
            description = "Возвращает список всех департаментов")
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Operation(summary = "Обновить департамент",
            description = "Обновляет информацию о департаменте на основе переданных данных")
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

    @Operation(summary = "Удалить департамент",
            description = "Удаляет департамент по его уникальному идентификатору")
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }
}
