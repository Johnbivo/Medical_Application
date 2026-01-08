package com.bivolaris.KavalaMed.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditid")
    private Integer auditId;

    @NotNull
    @Column(name = "tablename")
    private String tableName;

    @NotNull
    @Column(name = "recordid")
    private Integer recordId;

    @NotNull
    @Column(name = "operation")
    private String operation;

    @NotNull
    @Column(name = "changedby")
    private Integer changedBy;

    @Column(name = "changedate")
    private Instant changeDate = Instant.now();
}