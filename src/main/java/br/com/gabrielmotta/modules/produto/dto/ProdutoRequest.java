package br.com.gabrielmotta.modules.produto.dto;

import lombok.Builder;

@Builder
public record ProdutoRequest(String descricao, Double preco) {
}
