package br.com.gabrielmotta.modules.dto;

import br.com.gabrielmotta.modules.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {

    private String descricaoProduto;

    public static ProdutoResponse of(Produto produto) {
        return ProdutoResponse.builder()
                .descricaoProduto(produto.toString())
                .build();
    }
}
