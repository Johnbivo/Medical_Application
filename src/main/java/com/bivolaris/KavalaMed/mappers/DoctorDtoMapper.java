package com.bivolaris.KavalaMed.mappers;


import com.bivolaris.KavalaMed.dtos.DoctorDto;
import com.bivolaris.KavalaMed.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  DoctorDtoMapper {

    @Mapping(source = "doctorId", target = "doctorId")
    DoctorDto toDto(Doctor doctor);

}
