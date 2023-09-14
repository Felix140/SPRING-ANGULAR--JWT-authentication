package com.auth.jwt.backend.controllers;

import com.auth.jwt.backend.dto.SignUpDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auth.jwt.backend.dto.CredentialsDto;
import com.auth.jwt.backend.dto.UserDto;
import com.auth.jwt.backend.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController //! Se non si mette questa annotation appare ERRORE 403 nel FE
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
		//Chiamo il service del USER
		UserDto user = userService.login(credentialsDto);
		return ResponseEntity.ok(user);
	}

	//? Qui creo una nuova DTO = SignUpDto
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
		//Chiamo il service del USER
		UserDto user = userService.register(signUpDto);
		//Ritorno un response di tipo CREATED che contiene l'URL dell'ENTITY + il BODY
		return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
	}
}
