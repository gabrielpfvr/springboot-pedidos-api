package br.com.xbrain.api.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.xbrain.api.model.DetalhesErro;
import br.com.xbrain.api.service.PedidoNaoEncontradoException;

@RestControllerAdvice
public class PedidoExceptionHandler {
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<DetalhesErro> handlerPedidoNaoEncontrado(PedidoNaoEncontradoException ex, HttpServletRequest req) {
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404L);
		erro.setMensagem("Pedido não encontrado. Verifique se o ID está correto.");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
