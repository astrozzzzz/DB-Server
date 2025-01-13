package ru.lazarev.db_server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_project")
@Schema(description = "Сущность для связи сотрудников и проектов")
public class EmployeeProject {

    @EmbeddedId
    @Schema(description = "Составной ключ, включающий идентификаторы сотрудника и проекта")
    private EmployeeProjectId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    @Schema(description = "Сотрудник, связанный с проектом")
    private Employee employee;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    @Schema(description = "Проект, связанный с сотрудником")
    private Project project;
}
