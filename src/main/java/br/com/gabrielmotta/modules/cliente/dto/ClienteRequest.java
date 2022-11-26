package br.com.gabrielmotta.modules.cliente.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ClienteRequest(String nome, String cpf, String email, String telefone,
                             List<EnderecoRequest> enderecosEntregaRequest) {
}
