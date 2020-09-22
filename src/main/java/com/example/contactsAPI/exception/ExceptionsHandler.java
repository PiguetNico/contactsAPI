package com.example.contactsAPI.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionsHandler
{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest)
	{
		ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
		
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalException(Exception exception, WebRequest webRequest)
	{
		ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> validationFields(MethodArgumentNotValidException exception)
	{
		ErrorMessage errorMessage = new ErrorMessage(new Date(), "Validation error", exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}