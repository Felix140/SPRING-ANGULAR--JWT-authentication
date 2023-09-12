package com.auth.jwt.backend.mappers;

import org.mapstruct.Mapper;

import com.auth.jwt.backend.dto.UserDto;
import com.auth.jwt.backend.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserDto toUserDto(User user);
}
