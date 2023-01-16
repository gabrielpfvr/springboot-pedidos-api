package br.com.gabrielmotta.modules.helper;

import br.com.gabrielmotta.modules.cliente.dto.*;
import br.com.gabrielmotta.modules.cliente.model.Cliente;
import br.com.gabrielmotta.modules.cliente.model.Endereco;

import java.util.List;

public class ClienteHelper {

    public static ClienteRequest clienteRequest() {
        return ClienteRequest.builder()
            .nome("Gabriel")
            .cpf("12345678900")
            .email("gabriel@teste.com")
            .telefone("43988776655")
            .enderecosEntregaRequest(List.of(enderecoRequest()))
            .build();
    }

    public static EnderecoRequest enderecoRequest() {
        return EnderecoRequest.builder()
            .estado("PR")
            .cidade("Londrina")
            .logradouro("Avenida Higienopolis")
            .numero(1000)
            .bairro("Centro")
            .cep("12345678")
            .build();
    }

    public static Cliente cliente() {
        return Cliente.builder()
            .id(1)
            .nome("Gabriel")
            .cpf("12345678900")
            .email("gabriel@teste.com")
            .telefone("43988776655")
            .enderecosEntrega(List.of(endereco()))
            .build();
    }

    public static ClientePedidoResponse clientePedidoResponse() {
        return ClientePedidoResponse.builder()
            .id(1)
            .nome("Gabriel")
            .cpf("12345678900")
            .email("gabriel@teste.com")
            .telefone("43988776655")
            .build();
    }

    public static Endereco endereco() {
        return Endereco.builder()
            .id(1)
            .estado("PR")
            .cidade("Londrina")
            .logradouro("Avenida Higienopolis")
            .numero(1000)
            .bairro("Centro")
            .cep("12345678")
            .build();
    }

    public static EnderecoResponse enderecoResponse() {
        return EnderecoResponse.builder()
            .id(1)
            .estado("PR")
            .cidade("Londrina")
            .logradouro("Avenida Higienopolis")
            .numero(1000)
            .bairro("Centro")
            .cep("12345678")
            .build();
    }
}
