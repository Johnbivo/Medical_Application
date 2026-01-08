package com.bivolaris.KavalaMed.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "housecalls")
public class HouseCall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "housecallid")
    private Integer houseCallId;

    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorid")
    private Doctor doctor;

    @Column(name = "requestdate")
    private Instant requestDate = Instant.now();

    @Column(name = "scheduleddate")
    private Instant scheduledDate;

    @NotNull
    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "housecall_status_enum")
    private Status status = Status.Requested;

    @Column(name = "notes")
    private String notes;

    public enum Status {
        Requested, Scheduled, Completed, Cancelled
    }
}