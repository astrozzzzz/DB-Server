package ru.lazarev.db_server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "project")
@Schema(description = "Сущность проекта")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    @Schema(description = "Уникальный идентификатор проекта", example = "1")
    private Long projectId;

    @Column(name = "project_name", nullable = false, length = 100)
    @Schema(description = "Название проекта", example = "Разработка корпоративного сайта")
    private String projectName;

    @Column(name = "description")
    @Schema(description = "Описание проекта", example = "Проект по созданию современного корпоративного веб-сайта для компании")
    private String description;

    @Column(name = "budget", precision = 14, scale = 2)
    @Schema(description = "Бюджет проекта", example = "250000.00")
    private BigDecimal budget;

    @Column(name = "deadline")
    @Schema(description = "Крайний срок завершения проекта", example = "2025-12-31")
    private LocalDate deadline;
}
