package com.bivolaris.KavalaMed.mappers;

import com.bivolaris.KavalaMed.dtos.SecretaryRegisterRequest;
import com.bivolaris.KavalaMed.entities.Secretary;
import com.bivolaris.KavalaMed.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SecretaryRegisterRequestMapper {


    User toUser(SecretaryRegisterRequest request);

    Secretary toSecretary(SecretaryRegisterRequest request);
}