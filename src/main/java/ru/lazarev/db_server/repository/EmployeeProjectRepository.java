package ru.lazarev.db_server.repository;

import ru.lazarev.db_server.entity.Employee;
import ru.lazarev.db_server.entity.EmployeeProject;
import ru.lazarev.db_server.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {

    // Найти проекты по ID сотрудника
    @Query("SELECT ep.project FROM EmployeeProject ep WHERE ep.employee.employeeId = :employeeId")
    List<Project> findProjectsByEmployeeId(@Param("employeeId") UUID employeeId);

    // Найти сотрудников по ID проекта
    @Query("SELECT ep.employee FROM EmployeeProject ep WHERE ep.project.projectId = :projectId")
    List<Employee> findEmployeesByProjectId(@Param("projectId") Long projectId);

    // Найти связь между сотрудником и проектом
    @Query("SELECT ep FROM EmployeeProject ep WHERE ep.employee.employeeId = :employeeId AND ep.project.projectId = :projectId")
    Optional<EmployeeProject> findByEmployeeIdAndProjectId(@Param("employeeId") UUID employeeId, @Param("projectId") Long projectId);
}