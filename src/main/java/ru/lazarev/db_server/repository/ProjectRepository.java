package ru.lazarev.db_server.repository;

import ru.lazarev.db_server.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Найти проекты по дедлайну
    List<Project> findByDeadline(LocalDate deadline);

    // Найти проекты с бюджетом больше указанного
    List<Project> findByBudgetGreaterThan(BigDecimal budget);
}