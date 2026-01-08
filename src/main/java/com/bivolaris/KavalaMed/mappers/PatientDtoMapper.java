package com.bivolaris.KavalaMed.mappers;



import com.bivolaris.KavalaMed.dtos.PatientDto;
import com.bivolaris.KavalaMed.entities.Patient;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PatientDtoMapper {

    PatientDto toDto(Patient patient);
}
