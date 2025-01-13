package ru.lazarev.db_server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "employee")
@Schema(description = "Сущность сотрудника")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "employee_id", columnDefinition = "UUID")
    @Schema(description = "Уникальный идентификатор сотрудника", example = "85440838-c5a5-4dab-9af5-d7fb74bfaaed")
    private UUID employeeId;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @Schema(description = "Департамент, к которому относится сотрудник")
    private Department department;

    @Column(name = "first_name", nullable = false, length = 50)
    @Schema(description = "Имя сотрудника", example = "Иван")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Schema(description = "Фамилия сотрудника", example = "Иванов")
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    @Schema(description = "Дата рождения сотрудника", example = "1990-01-01")
    private LocalDate birthDate;

    @Column(name = "start_time", nullable = false)
    @Schema(description = "Время начала рабочего дня сотрудника", example = "09:00:00")
    private LocalTime startTime;

    @Column(name = "email", length = 100)
    @Schema(description = "Электронная почта сотрудника", example = "ivanov@example.com")
    private String email;

    @Column(name = "notes")
    @Schema(description = "Дополнительные заметки о сотруднике", example = "Профессионал с опытом работы более 5 лет")
    private String notes;
}
