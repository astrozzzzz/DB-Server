package ru.lazarev.db_server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "department")
@Schema(description = "Сущность департамента")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    @Schema(description = "Уникальный идентификатор департамента", example = "1")
    private Long departmentId;

    @Column(name = "name", nullable = false, length = 100)
    @Schema(description = "Название департамента", example = "Финансовый отдел")
    private String name;

    @Column(name = "description")
    @Schema(description = "Описание департамента", example = "Отдел, отвечающий за управление финансами")
    private String description;

    @Column(name = "budget", precision = 12, scale = 2)
    @Schema(description = "Бюджет департамента", example = "1000000.00")
    private BigDecimal budget;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Дата и время создания департамента", example = "2023-01-01T12:00:00")
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    @Schema(description = "Статус активности департамента", example = "true")
    private Boolean isActive = true;

    @Column(name = "ip_address")
    @Schema(description = "IP-адрес, связанный с департаментом", example = "192.168.1.1")
    private String ipAddress;
}
