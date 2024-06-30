package com.restful.exceptionhandlers;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UsersExceptionHandlers extends ResponseEntityExceptionHandler {

	// this method allows to handled any exception, we need to provide the class for
	// which we need to handled, for example
	// of we need to handled null pointer exception then we need to provide that
	// class and also we need to provide the same class as argument
	// eg : NullPointerException. class and change the argument as
	// NullPointerException ex

//	@ExceptionHandler(value = {Exception.class})
//	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
//		
//		return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}

//	to handle null pointer exception
	@ExceptionHandler(value = { NullPointerException.class, UserServiceException.class })
	public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request) {

		CustomErrorResponse errorResponse = new CustomErrorResponse(new Date(), ex.getLocalizedMessage());

		return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	// to handle specific exception
//	@ExceptionHandler(value = {UserServiceException.class})
//	public ResponseEntity<Object> handleUserSpecificException(UserServiceException ex, WebRequest request){
//		
//		CustomErrorResponse errorResponse = new CustomErrorResponse(new Date(), ex.getLocalizedMessage());
//				
//		return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
//	}


}
