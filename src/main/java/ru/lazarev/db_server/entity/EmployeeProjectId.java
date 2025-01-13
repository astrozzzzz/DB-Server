package ru.lazarev.db_server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Schema(description = "Составной ключ для связи сотрудника и проекта")
public class EmployeeProjectId implements Serializable {

    @Column(name = "employee_id", columnDefinition = "UUID")
    @Schema(description = "Идентификатор сотрудника", example = "85440838-c5a5-4dab-9af5-d7fb74bfaaed")
    private UUID employeeId;

    @Column(name = "project_id")
    @Schema(description = "Идентификатор проекта", example = "1")
    private Long projectId;
}
