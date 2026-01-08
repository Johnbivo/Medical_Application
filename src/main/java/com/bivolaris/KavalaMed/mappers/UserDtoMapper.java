package com.bivolaris.KavalaMed.mappers;


import com.bivolaris.KavalaMed.dtos.UserDto;
import com.bivolaris.KavalaMed.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(target = "role", expression = "java(user.getRole() != null ? user.getRole().toString() : null)")
    UserDto toDto(User user);
}
