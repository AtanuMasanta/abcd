package com.user.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(InvalidataException.class)
	public ResponseEntity<String> exceptionHandlermethod1(InvalidataException e)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(NoUserPresentException.class)
	public ResponseEntity<String> exceptionHandlermethod2()
	{
		String str="opps !! at this moment no data is present in data base";
		return ResponseEntity.status(HttpStatus.OK).body(str);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> exceptionHandlermethod3(UserNotFoundException e)
	{
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(InvalidCredential.class)
	public ResponseEntity<String> exceptionHandlermethod5(InvalidCredential e)
	{
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandlermethod4(Exception e)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	
	
	

}

