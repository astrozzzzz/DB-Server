package ru.lazarev.db_server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name", nullable = false, length = 100)
    private String projectName;

    @Column(name = "description")
    private String description;

    @Column(name = "budget", precision = 14, scale = 2)
    private BigDecimal budget;

    @Column(name = "deadline")
    private LocalDate deadline;
}