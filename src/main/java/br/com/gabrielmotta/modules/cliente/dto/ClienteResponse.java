package br.com.gabrielmotta.modules.cliente.dto;

import br.com.gabrielmotta.modules.cliente.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private String nome;
    private EnderecoResponse endereco;

    public static ClienteResponse of(Cliente cliente) {
        return ClienteResponse.builder()
                .nome(cliente.getNome())
                .endereco(EnderecoResponse.of(cliente.getEndereco()))
                .build();
    }
}
