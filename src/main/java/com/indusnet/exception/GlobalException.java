package com.indusnet.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyError> userExceptionHandler(HttpStatus status,WebRequest wb, UserException ue ){
		MyError myError = new MyError(status, wb.getDescription(false), ue.getMessage(), LocalDate.now());
		
		return ResponseEntity.badRequest().body(myError);
		
	}
}
