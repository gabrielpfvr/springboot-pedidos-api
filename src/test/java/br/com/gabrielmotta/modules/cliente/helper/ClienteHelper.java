package br.com.gabrielmotta.modules.cliente.helper;

import br.com.gabrielmotta.modules.cliente.dto.ClienteRequest;
import br.com.gabrielmotta.modules.cliente.dto.EnderecoRequest;

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
}
