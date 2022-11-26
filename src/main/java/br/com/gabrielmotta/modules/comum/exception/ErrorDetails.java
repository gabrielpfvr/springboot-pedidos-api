package br.com.gabrielmotta.modules.comum.exception;

import lombok.Data;

@Data
public class ErrorDetails {

	private String message;

	public ErrorDetails(String message) {
		this.message = message;
	}
}
