package br.com.gabrielmotta.modules.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PedidoExceptionHandler {
	
	@ExceptionHandler(PedidoException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public DetalhesErro pedidoNaoEncontradoError(PedidoException ex) {
		return new DetalhesErro(ex.getMessage());
	}
}
