package br.com.gabrielmotta.modules.produto.dto;

import br.com.gabrielmotta.modules.produto.model.Produto;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

public record ProdutoResponse(Integer id, String descricao, Double preco) {

    public static ProdutoResponse of(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getDescricao(),
                produto.getPreco()
        );
    }

    public static List<ProdutoResponse> of(List<Produto> produtos) {
        return !isEmpty(produtos)
                ? produtos.stream()
                    .map(ProdutoResponse::of)
                    .toList()
                : Collections.emptyList();
    }
}
