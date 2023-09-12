package com.auth.jwt.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// UserDto sara il nostro POJO
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String login;
	private String token;
}
