package com.labcorp.hr.workvacationtrack.controller;

import java.rmi.NoSuchObjectException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvise {
	
	@ExceptionHandler(NoSuchObjectException.class)
	public ResponseEntity<String> handleException(NoSuchObjectException e) {
		return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
	}
}
