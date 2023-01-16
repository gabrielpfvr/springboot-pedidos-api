package br.com.gabrielmotta.modules.comum.controller;

import br.com.gabrielmotta.modules.comum.exception.ErrorDetails;
import br.com.gabrielmotta.modules.comum.exception.NotFoundException;
import br.com.gabrielmotta.modules.comum.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDetails> validationError(Exception ex) {
        BindingResult result;
        if (ex instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) ex).getBindingResult();
        } else {
            result = ((BindException) ex).getBindingResult();
        }
        return result.getFieldErrors()
            .stream()
            .map(err ->
                new ErrorDetails(String.format("O campo %s %s", err.getField(), err.getDefaultMessage()), err.getField())
            )
            .collect(Collectors.toList());
    }

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
