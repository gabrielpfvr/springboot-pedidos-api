package br.com.gabrielmotta.modules.cliente.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.gabrielmotta.modules.helper.ClienteHelper.enderecoRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class EnderecoTest {

    @Test
    void of_retornarEndereco_aPartirDeRequest() {
        assertThat(Endereco.of(enderecoRequest()))
            .extracting("estado", "cidade", "logradouro", "numero", "bairro", "cep")
            .containsExactly("PR", "Londrina", "Avenida Higienopolis", 1000, "Centro", "12345678");
    }

    @Test
    void of_retornarListaDeEnderecos_aPartirDeRequest() {
        assertThat(Endereco.of(List.of(enderecoRequest())))
            .extracting("estado", "cidade", "logradouro", "numero", "bairro", "cep")
            .contains(
                tuple("PR", "Londrina", "Avenida Higienopolis", 1000, "Centro", "12345678"),
                tuple("PR", "Londrina", "Avenida Higienopolis", 1000, "Centro", "12345678")
            );
    }
}