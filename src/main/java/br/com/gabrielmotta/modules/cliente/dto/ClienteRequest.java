package br.com.gabrielmotta.modules.cliente.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
public record ClienteRequest(@NotBlank String nome, @NotBlank String cpf, @NotBlank String email, @NotBlank String telefone,
                             List<EnderecoRequest> enderecosEntregaRequest) {
}
