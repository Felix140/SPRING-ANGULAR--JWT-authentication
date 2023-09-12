package com.auth.jwt.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auth.jwt.backend.dto.CredentialsDto;
import com.auth.jwt.backend.dto.UserDto;
import com.auth.jwt.backend.services.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
		//Chiamo il service del USER
		UserDto user = userService.login(credentialsDto);
		return ResponseEntity.ok(user);
	}
}
