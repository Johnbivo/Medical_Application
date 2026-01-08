package com.bivolaris.KavalaMed.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
public class PatientDto {

    private Integer patientId;

    @NotNull
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String city;
    private LocalDate dateOfBirth;

}
