package br.com.gabrielmotta.modules.pedido.dto;

import br.com.gabrielmotta.modules.cliente.dto.ClientePedidoResponse;
import br.com.gabrielmotta.modules.cliente.dto.EnderecoResponse;
import br.com.gabrielmotta.modules.pedido.enums.EStatusPedido;
import br.com.gabrielmotta.modules.pedido.model.Pedido;
import br.com.gabrielmotta.modules.produto.dto.ProdutoResponse;
import br.com.gabrielmotta.modules.produto.model.Produto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Builder
public record PedidoResponse(Integer id, LocalDateTime dataCriacao, ClientePedidoResponse cliente, List<ProdutoResponse> produtos,
                             EnderecoResponse enderecoEntrega, EStatusPedido status, Double valorTotal) {

    public static PedidoResponse of(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getDataCriacao(),
                ClientePedidoResponse.of(pedido.getCliente()),
                pedido.getProdutos()
                    .stream()
                    .map(ProdutoResponse::of)
                    .collect(Collectors.toList()),
                pedido.getEnderecoEntrega(),
                pedido.getStatusPedido(),
                pedido.getProdutos()
                    .stream()
                    .mapToDouble(Produto::getPreco)
                    .sum()
        );
    }

    public static List<PedidoResponse> of(List<Pedido> pedidos) {
        return !isEmpty(pedidos)
                ? pedidos.stream()
                    .map(PedidoResponse::of)
                    .toList()
                : Collections.emptyList();
    }
}
