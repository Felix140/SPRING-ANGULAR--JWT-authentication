package com.auth.jwt.backend.mappers;

import com.auth.jwt.backend.dto.SignUpDto;
import org.mapstruct.Mapper;

import com.auth.jwt.backend.dto.UserDto;
import com.auth.jwt.backend.entities.User;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toUserDto(User user);

	@Mapping(target = "password", ignore = true) //? ignora password perchè ha 2 formati (CHAR[] e STRING)
	User signUpToUser(SignUpDto signUpDto);
}
