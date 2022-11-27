package br.com.gabrielmotta.modules.produto.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public record ProdutoRequest(@NotBlank String descricao, @NotNull Double preco) {
}
