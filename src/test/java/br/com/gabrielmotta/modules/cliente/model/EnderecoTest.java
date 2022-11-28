package br.com.gabrielmotta.modules.cliente.model;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.gabrielmotta.modules.cliente.helper.ClienteHelper.enderecoRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class EnderecoTest {

    @Test
    public void of_retornarEndereco_aPartirDeRequest() {
        assertThat(Endereco.of(enderecoRequest()))
            .extracting("estado", "cidade", "logradouro", "numero", "bairro", "cep")
            .containsExactly("PR", "Londrina", "Avenida Higienopolis", 1000, "Centro", "12345678");
    }

    @Test
    public void of_retornarListaDeEnderecos_aPartirDeRequest() {
        assertThat(Endereco.of(List.of(enderecoRequest())))
            .extracting("estado", "cidade", "logradouro", "numero", "bairro", "cep")
            .contains(
                tuple("PR", "Londrina", "Avenida Higienopolis", 1000, "Centro", "12345678"),
                tuple("PR", "Londrina", "Avenida Higienopolis", 1000, "Centro", "12345678")
            );
    }
}