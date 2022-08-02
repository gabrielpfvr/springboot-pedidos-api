package br.com.gabrielmotta.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {

    @NotNull
    private Integer clienteId;
    @NotEmpty
    private List<Integer> produtosIds;
}
