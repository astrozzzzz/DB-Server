package ru.lazarev.db_server.controller;

import ru.lazarev.db_server.entity.Employee;
import ru.lazarev.db_server.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee", description = "API для управления сотрудниками")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Operation(summary = "Создать нового сотрудника",
            description = "Добавляет нового сотрудника в базу данных")
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @Operation(summary = "Получить сотрудника по ID",
            description = "Возвращает информацию о сотруднике по его уникальному идентификатору")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Operation(summary = "Получить список всех сотрудников",
            description = "Возвращает список всех сотрудников")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Operation(summary = "Обновить данные сотрудника",
            description = "Обновляет данные сотрудника по его ID")
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable UUID id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setBirthDate(employeeDetails.getBirthDate());
        employee.setStartTime(employeeDetails.getStartTime());
        employee.setEmail(employeeDetails.getEmail());
        employee.setNotes(employeeDetails.getNotes());
        return employeeRepository.save(employee);
    }

    @Operation(summary = "Удалить сотрудника",
            description = "Удаляет сотрудника из базы данных по его ID")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeRepository.deleteById(id);
    }
}
