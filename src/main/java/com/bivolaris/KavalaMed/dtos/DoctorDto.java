package com.bivolaris.KavalaMed.dtos;

import com.bivolaris.KavalaMed.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class DoctorDto {

    private Long doctorId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Doctor.Specialty specialty;
    @NotNull
    private String phone;
    @NotNull
    String email;
    private Boolean houseCallsAvailable;
}