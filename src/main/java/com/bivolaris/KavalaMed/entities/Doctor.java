package com.bivolaris.KavalaMed.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorid")
    private Integer doctorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @NotNull
    @Column(name = "firstname")
    private String firstName;

    @NotNull
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "specialty", columnDefinition = "specialty_enum")
    private Specialty specialty;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "housecallsavailable")
    private Boolean houseCallsAvailable = false;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private Set<HouseCall> houseCalls;

    @OneToMany(mappedBy = "doctor")
    private Set<MedicalRecord> medicalRecords;

    public enum Specialty {
        GeneralMedicine, Pneumonology, Cardiology
    }
}