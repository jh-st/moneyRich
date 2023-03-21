package com.money.rich.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.money.rich.common.utility.ResponseUtils;

@RestController
@RestControllerAdvice
public final class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		return new ResponseEntity<>(ResponseUtils.getResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),
			ex.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({BindException.class})
	public ResponseEntity<Object> handlerBindException(BindException ex, WebRequest request) {
		return new ResponseEntity<>(ResponseUtils.getResult(HttpStatus.BAD_REQUEST.value(),
			ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(),
			request.getDescription(false)), HttpStatus.BAD_REQUEST);
	}

}
