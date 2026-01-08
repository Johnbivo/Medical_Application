package com.bivolaris.KavalaMed.dtos;


import com.bivolaris.KavalaMed.entities.Appointment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AppointmentDto {

    private Integer appointmentId;

    @NotNull
    private String patientLastName; // ←

    @NotNull
    private String doctorLastName; // ←

    private Date appointmentDate;

    private String reason;

    @NotNull
    private Appointment.Status status;

    private Appointment.AppointmentType appointmentType;

    private BigDecimal cost;

    private Appointment.PaymentStatus paymentStatus;
}
