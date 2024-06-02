package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "birthDate", target = "birthDate")
    UserDto toDto(User user);

    @Mapping(source = "birthDate", target = "birthDate")
    User toEntity(UserDto userDto);
}
