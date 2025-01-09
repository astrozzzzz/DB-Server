package ru.lazarev.db_server.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
public class EmployeeProjectId implements Serializable {

    @Column(name = "employee_id", columnDefinition = "UUID")
    private UUID employeeId;

    @Column(name = "project_id")
    private Long projectId;
}