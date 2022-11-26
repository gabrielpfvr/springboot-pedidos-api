package br.com.gabrielmotta.modules.cliente.dto;

import br.com.gabrielmotta.modules.cliente.model.Cliente;
import lombok.Builder;

import java.util.List;

@Builder
public record ClienteResponse(String nome, String cpf, String email, String telefone, List<EnderecoResponse> enderecosEntrega) {

    public static ClienteResponse of(Cliente cliente) {
        return new ClienteResponse(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                EnderecoResponse.of(cliente.getEnderecosEntrega())
        );
    }
}
