package com.alejfneto.desafio_03.controlers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alejfneto.desafio_03.dto.CustomError;
import com.alejfneto.desafio_03.dto.ValidationError;
import com.alejfneto.desafio_03.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler (ResourceNotFoundException.class)
	public ResponseEntity<CustomError> resorceNotFound(ResourceNotFoundException e, HttpServletRequest request){
	HttpStatus status = HttpStatus.NOT_FOUND;
	CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler (MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request){
	HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
	ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados Invalidos!", request.getRequestURI());
	for (FieldError fe : e.getBindingResult().getFieldErrors()) {
		err.adderrors(fe.getField(), fe.getDefaultMessage());
	}
	return ResponseEntity.status(status).body(err);
	}
	
}
