package com.bivolaris.KavalaMed.mappers;


import com.bivolaris.KavalaMed.dtos.AppointmentDto;
import com.bivolaris.KavalaMed.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentDtoMapper {

    @Mapping(source="patient.lastName", target = "patientLastName")
    @Mapping(source="doctor.lastName", target="doctorLastName")
    AppointmentDto toDto(Appointment appointment);
}
