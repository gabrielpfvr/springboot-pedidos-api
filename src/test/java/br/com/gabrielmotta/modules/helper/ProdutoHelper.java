package br.com.gabrielmotta.modules.helper;

import br.com.gabrielmotta.modules.produto.dto.ProdutoRequest;
import br.com.gabrielmotta.modules.produto.model.Produto;

public class ProdutoHelper {

    public static Produto produto(Integer id) {
        return Produto.builder()
            .id(id)
            .descricao("Produto 0" + id)
            .preco(2000.0)
            .build();
    }

    public static ProdutoRequest produtoRequest() {
        return ProdutoRequest.builder()
            .descricao("Produto")
            .preco(2000.0)
            .build();
    }
}
