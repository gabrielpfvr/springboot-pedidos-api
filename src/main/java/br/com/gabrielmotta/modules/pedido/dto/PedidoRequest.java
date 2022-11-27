package br.com.gabrielmotta.modules.pedido.dto;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
public record PedidoRequest(@NotNull Integer clienteId,
                            @NotEmpty List<Integer> produtosIds,
                            @NotNull Integer enderecoEntregaId) {
}
