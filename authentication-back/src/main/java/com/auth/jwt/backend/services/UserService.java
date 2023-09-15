package com.auth.jwt.backend.services;

import java.nio.CharBuffer;
import java.util.Optional;

import com.auth.jwt.backend.dto.SignUpDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.jwt.backend.dto.CredentialsDto;
import com.auth.jwt.backend.dto.UserDto;
import com.auth.jwt.backend.entities.User;
import com.auth.jwt.backend.exceptions.AppException;
import com.auth.jwt.backend.mappers.UserMapper;
import com.auth.jwt.backend.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

//	Metto qui tutte le dipendenze
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

	public UserDto login(CredentialsDto credentialsDto) {

		User user = userRepo.findByLogin(credentialsDto.login())
				.orElseThrow(() -> new AppException("Utente Sconosciuto", HttpStatus.NOT_FOUND));

		if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
			return userMapper.toUserDto(user);
		}

		throw new AppException("Password non valida", HttpStatus.BAD_REQUEST);
	}

	public UserDto register(SignUpDto signUpDto) {
		Optional<User> oUser = userRepo.findByLogin(signUpDto.login());

		//? qui ritorno un EXCEPTION se l'UTENTE è già presente nel DB
		if(oUser.isPresent()) {
			throw new AppException("Utente già Loggato", HttpStatus.BAD_REQUEST);
		}

		//? ALTRIMENTI mappa il DTO nell'ENTITY...
		User user = userMapper.signUpToUser(signUpDto);

		//? ...codifica la PASSWORD...
		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));

		//? ...e salva l'ENTITY
		User savedUser = userRepo.save(user);

		return userMapper.toUserDto(savedUser);
	}

	public UserDto findByLogin(String login) {
		User user = userRepo.findByLogin(login)
				.orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
		return userMapper.toUserDto(user);
	}


}
