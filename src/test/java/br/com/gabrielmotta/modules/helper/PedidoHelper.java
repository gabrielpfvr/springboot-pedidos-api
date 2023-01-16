package br.com.gabrielmotta.modules.helper;

import br.com.gabrielmotta.modules.pedido.dto.PedidoRequest;
import br.com.gabrielmotta.modules.pedido.enums.EStatusPedido;
import br.com.gabrielmotta.modules.pedido.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.gabrielmotta.modules.helper.ClienteHelper.cliente;
import static br.com.gabrielmotta.modules.helper.ProdutoHelper.produto;

public class PedidoHelper {

    public static Pedido pedido(Integer id) {
        return Pedido.builder()
            .id(id)
            .dataCriacao(LocalDateTime.of(2022, 1, 15, 8, 0))
            .cliente(cliente())
            .produtos(List.of(produto(id)))
            .enderecoEntregaId(1)
            .statusPedido(EStatusPedido.CRIADO)
            .build();
    }

    public static PedidoRequest pedidoRequest(Integer clienteId, List<Integer> produtosIds, Integer enderecoEntregaId) {
        return PedidoRequest.builder()
            .clienteId(clienteId)
            .produtosIds(produtosIds)
            .enderecoEntregaId(enderecoEntregaId)
            .build();
    }
}
