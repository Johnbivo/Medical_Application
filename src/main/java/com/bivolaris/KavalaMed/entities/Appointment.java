package com.bivolaris.KavalaMed.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentid")
    private Integer appointmentId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patient;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "doctorid")
    private Doctor doctor;

    @Column(name = "appointmentdate")
    private Instant appointmentDate;

    @Column(name = "reason")
    private String reason;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "status_enum")
    private Status status;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "appointmenttype", columnDefinition = "appointment_type_enum")
    private AppointmentType appointmentType;

    @Column(name = "cost")
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "paymentstatus", columnDefinition = "payment_status_enum")
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "appointment")
    private Set<Payment> payments;

    public enum Status {
        Scheduled, Completed, Cancelled
    }

    public enum AppointmentType {
        InOffice, Telemedicine, HouseCall
    }

    public enum PaymentStatus {
        Pending, Paid, Refunded
    }
}