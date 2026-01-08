package com.bivolaris.KavalaMed.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "medicalrecords")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recordid")
    private Integer recordId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patient;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "doctorid")
    private Doctor doctor;

    @Column(name = "visitdate")
    private LocalDate visitDate;

    @Column(name = "notes")
    private String notes;
}