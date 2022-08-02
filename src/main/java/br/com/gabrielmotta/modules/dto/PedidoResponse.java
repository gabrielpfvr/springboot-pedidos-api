package br.com.gabrielmotta.modules.dto;

import br.com.gabrielmotta.modules.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {

    private Integer id;
    private LocalDateTime dataCriacao;
    private ClienteResponse cliente;
    private List<ProdutoResponse> produtos;

    public static PedidoResponse of(Pedido pedido) {
        return PedidoResponse.builder()
                .id(pedido.getId())
                .dataCriacao(pedido.getDataCriacao())
                .cliente(ClienteResponse.of(pedido.getCliente()))
                .produtos(pedido.getProdutos()
                        .stream()
                        .map(ProdutoResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
