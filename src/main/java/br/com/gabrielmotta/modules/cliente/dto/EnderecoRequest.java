package br.com.gabrielmotta.modules.cliente.dto;

import lombok.Builder;

@Builder
public record EnderecoRequest(String estado, String cidade, String logradouro, Integer numero, String complemento,
                              String cep, String bairro, String referencia) {
}
