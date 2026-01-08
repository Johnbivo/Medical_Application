package com.bivolaris.KavalaMed.mappers;


import com.bivolaris.KavalaMed.dtos.DoctorRegisterRequest;
import com.bivolaris.KavalaMed.entities.Doctor;
import com.bivolaris.KavalaMed.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorRegisterRequestMapper {

    User toUser(DoctorRegisterRequest request);
    Doctor toDoctor(DoctorRegisterRequest request);
}

