package com.usermanagement.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.usermanagement.model.ResponseHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DuplicateDataException.class)
	public ResponseEntity<?> handleConflict(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<ResponseHandler>(new ResponseHandler( HttpStatus.CONFLICT,LocalDateTime.now(),ex.getMessage(),null),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> dataNotFoundException(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<ResponseHandler>(new ResponseHandler( HttpStatus.NO_CONTENT,LocalDateTime.now(),ex.getMessage(),null),HttpStatus.CONFLICT);
	}
}
