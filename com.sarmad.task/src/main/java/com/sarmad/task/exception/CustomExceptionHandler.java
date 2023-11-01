package com.sarmad.task.exception;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> resourceNotFoundException(UserAlreadyExistsException ex, WebRequest request) {
		//TODO: remove this as it is for debugging purpose.
		ex.printStackTrace();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler(PremessionException.class)
//	public ResponseEntity<?> notValidUserException(PremessionException ex, WebRequest request) {
//		//TODO: remove this as it is for debugging purpose.
//		ex.printStackTrace();
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
//	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
		//TODO: remove this as it is for debugging purpose.
		ex.printStackTrace();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex, WebRequest request) {
		//TODO: remove this as it is for debugging purpose.
		ex.printStackTrace();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), //ex.getCause().getCause().getMessage()
				ex.getMostSpecificCause().getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}



	

}
