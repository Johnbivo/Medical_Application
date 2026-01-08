package com.bivolaris.KavalaMed.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentid")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "appointmentid")
    private Appointment appointment;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "paymentmethod", columnDefinition = "payment_method_enum")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "paymentstatus",columnDefinition = "payment_status_enum")
    private PaymentStatus paymentStatus = PaymentStatus.Pending;

    @Column(name = "paymentdate")
    private Instant paymentDate;

    @Column(name = "createddate")
    private Instant createdDate = Instant.now();

    public enum PaymentMethod {
        Card, Cash, Transfer
    }

    public enum PaymentStatus {
        Pending, Paid, Refunded
    }
}
