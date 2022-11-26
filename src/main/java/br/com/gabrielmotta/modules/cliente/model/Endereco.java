package br.com.gabrielmotta.modules.cliente.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Endereco {

	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
}
