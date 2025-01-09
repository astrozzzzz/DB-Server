package ru.lazarev.db_server.repository;

import ru.lazarev.db_server.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    // Найти сотрудников по ID департамента
    List<Employee> findByDepartment_DepartmentId(Long departmentId);
}