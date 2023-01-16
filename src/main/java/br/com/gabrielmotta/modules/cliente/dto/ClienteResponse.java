package br.com.gabrielmotta.modules.cliente.dto;

import br.com.gabrielmotta.modules.cliente.model.Cliente;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Builder
public record ClienteResponse(Integer id, String nome, String cpf, String email, String telefone,
                              List<EnderecoResponse> enderecosEntrega) {

    public static ClienteResponse of(Cliente cliente) {
        return new ClienteResponse(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEmail(),
            cliente.getTelefone(),
            EnderecoResponse.of(cliente.getEnderecosEntrega())
        );
    }

    public static List<ClienteResponse> of(List<Cliente> cliente) {
        return !isEmpty(cliente)
            ? cliente.stream()
            .map(ClienteResponse::of)
            .toList()
            : Collections.emptyList();
    }
}
