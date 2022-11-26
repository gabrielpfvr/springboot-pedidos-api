package br.com.gabrielmotta.modules.comum.controller;

import br.com.gabrielmotta.modules.comum.exception.ErrorDetails;
import br.com.gabrielmotta.modules.comum.exception.NotFoundException;
import br.com.gabrielmotta.modules.comum.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorDetails notFoundException(NotFoundException ex) {
		return new ErrorDetails(ex.getMessage());
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDetails validationException(ValidationException ex) {
		return new ErrorDetails(ex.getMessage());
	}
}
