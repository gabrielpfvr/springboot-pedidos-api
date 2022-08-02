package br.com.gabrielmotta.modules.handler;

import lombok.Data;

@Data
public class DetalhesErro {

	private String message;

	public DetalhesErro(String message) {
		this.message = message;
	}
}
