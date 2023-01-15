package br.com.gabrielmotta.modules.cliente.dto;

import br.com.gabrielmotta.modules.cliente.model.Cliente;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

public record ClientePedidoResponse(Integer id, String nome, String cpf, String email, String telefone) {

    public static ClientePedidoResponse of(Cliente cliente) {
        return new ClientePedidoResponse(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEmail(),
            cliente.getTelefone()
        );
    }

    public static List<ClientePedidoResponse> of(List<Cliente> cliente) {
        return !isEmpty(cliente)
            ? cliente.stream()
            .map(ClientePedidoResponse::of)
            .toList()
            : Collections.emptyList();
    }
}
