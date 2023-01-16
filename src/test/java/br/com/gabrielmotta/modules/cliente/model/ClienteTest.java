package br.com.gabrielmotta.modules.cliente.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.gabrielmotta.modules.helper.ClienteHelper.clienteRequest;
import static org.assertj.core.api.Assertions.assertThat;

class ClienteTest {

    @Test
    void of_retornarCliente_aPartirDeRequest() {
        assertThat(Cliente.of(clienteRequest()))
            .extracting("nome", "cpf", "email", "telefone", "enderecosEntrega")
            .containsExactly(
                "Gabriel",
                "12345678900",
                "gabriel@teste.com",
                "43988776655",
                List.of(Endereco.builder()
                    .estado("PR")
                    .cidade("Londrina")
                    .logradouro("Avenida Higienopolis")
                    .numero(1000)
                    .bairro("Centro")
                    .cep("12345678")
                    .build()
                )
            );
    }
}