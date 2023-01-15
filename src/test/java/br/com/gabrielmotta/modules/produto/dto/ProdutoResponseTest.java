package br.com.gabrielmotta.modules.produto.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.gabrielmotta.modules.helper.ProdutoHelper.produto;
import static org.assertj.core.api.Assertions.assertThat;

class ProdutoResponseTest {

    @Test
    void of_retornarProduto_quandoChamado() {
        assertThat(produto(1))
            .extracting("id", "descricao", "preco")
            .containsExactly(1, "Produto 01", 2000.0);
    }

    @Test
    void of_retornarListaDeProduto_quandoChamado() {
        assertThat(List.of(produto(1), produto(2)))
            .flatExtracting("id", "descricao", "preco")
            .containsExactly(
                1, "Produto 01", 2000.0,
                2, "Produto 02", 2000.0
            );
    }
}