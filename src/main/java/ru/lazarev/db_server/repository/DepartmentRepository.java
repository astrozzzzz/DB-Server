package ru.lazarev.db_server.repository;

import ru.lazarev.db_server.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}