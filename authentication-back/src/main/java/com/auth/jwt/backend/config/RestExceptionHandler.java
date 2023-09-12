package com.auth.jwt.backend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.auth.jwt.backend.dto.ErrorDto;
import com.auth.jwt.backend.exceptions.AppException;


// @ControllerAdvice utilizzata per centralizzare il codice di gestione delle eccezioni in un'unica classe, 
// anziché distribuirlo in diversi controller.
@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value = { AppException.class })
	public ResponseEntity<ErrorDto> handleException(AppException ex) {
		return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorDto(ex.getMessage()));
	}

}
